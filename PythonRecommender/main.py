import mysql.connector
from mysql.connector import Error
import numpy as np
from numpy import dot
from numpy.linalg import norm
import math
from sklearn.metrics.pairwise import cosine_similarity

recommendQuantity = 10


def sort_tuples(kv): return (kv[1], kv[0])


def main(startIndex):
    id_distances = {}
    stats, maxCol = readStats(startIndex)
    norms = normalizeStats(stats, maxCol, startIndex)
    print('len of norms: ', len(norms))

    for subject in norms:
        cosines = []
        euclideans = []
        subject_id = subject[0]

        for object in norms:
            object_id = object[0]
            if subject_id != object_id:
                cosine = float(cosine_similarity(
                    [subject[startIndex:]], [object[startIndex:]]))
                euclidean = float(euclidean_distance(
                    subject[startIndex:], object[startIndex:]))
                cosines.append((object_id, cosine))
                euclideans.append((object_id, euclidean))

        cosines = sorted(cosines, key=sort_tuples, reverse=True)
        euclideans = sorted(euclideans, key=sort_tuples)
        id_distances[subject_id] = {
            'cosines': cosines[:recommendQuantity],
            'euclideans': euclideans[:recommendQuantity]
        }
    return id_distances


def normalizeStats(stats, maxCol, startIndex):
    table = []
    for row in stats:
        tmpRow = []
        for i in range(startIndex, len(row)):
            if maxCol[i] != 0:
                tmpRow.append(row[i]/maxCol[i])
            else:
                tmpRow.append(0)
        table.append([*row[0:startIndex], *tmpRow])
    return table


def euclidean_distance(x, y):
    return math.sqrt(sum([(a - b) ** 2 for a, b in zip(x, y)]))


def readStats(startIndex):
    stats = []
    maxs = []
    try:
        connection = getConnection()
        if connection.is_connected():
            cursor = connection.cursor()
            cursor.execute("select * from playerstats")
            records = cursor.fetchall()
            maxs = [0 for i in range(79)]
            for row in records:
                maxs = maxAggregateTuple(maxs, row)
                stats.append(row)
    finally:
        if (connection.is_connected()):
            cursor.close()
            connection.close()
            print('connection is closed')
    return stats, maxs


def writeResult(result):
    connection = None
    try:
        connection = getConnection()
        if connection.is_connected():
            cursor = connection.cursor()
            for player in result.items():
                _id = player[0]
                cosine_str = ''.join([str(i) + ';' for i in player[1]['cosines']])
                euclidean_str = ''.join([str(i) + ';' for i in player[1]['euclideans']])

                mySql_insert_query = """INSERT INTO playersimilarity(id, cosine, euclidean) VALUES (%s, %s, %s) """
                recordTuple = (_id, cosine_str, euclidean_str)
                cursor.execute(mySql_insert_query, recordTuple)
                connection.commit()
                print(f"Record {_id} inserted successfully into table")
    except mysql.connector.Error as e:
        print('Error when insert to database: ', e)
    finally:
        if (connection.is_connected()):
            cursor.close()
            connection.close()
            print('Connection is closed')


def getConnection(host='localhost', database='playerrecommender', user='khanh', password='12345'):
    try:
        connection = mysql.connector.connect(
            host=host, database=database, user=user, password=password)
        return connection
    except Error as e:
        print('Error when connecting to MySQL', e)
    return None


def maxAggregateTuple(tuple1, tuple2):
    tmp = []
    t1 = list(tuple1)
    t2 = list(tuple2)
    for i in range(len(t1)):
        tmp.append(max(t1[i], t2[i]))
    return tmp


def max(a, b):
    if a > b:
        return a
    return b


startIndex = 6
results = main(startIndex)
writeResult(results)

