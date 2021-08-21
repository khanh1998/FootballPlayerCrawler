<%-- 
    Document   : playerprofile
    Created on : Mar 21, 2020, 6:45:41 PM
    Author     : KHANHBQSE63463
--%>

<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Player Profile</title>
        <link rel="stylesheet" href="./css/index.css" type="text/css"/>
    </head>
    <body>
        <a href="DispatcherServlet">Home</a>
        <c:if test="${not empty sessionScope.USERNAME}">
            <p>Welcome ${sessionScope.NAME}</p>

            <c:set value="${sessionScope.FBREF}" var="fbrefs"/>
            <c:set var="isSaved" value="${false}" scope="page"/>
            <c:forEach var="item" items="${fbrefs}">
                <c:if test="${item eq requestScope.FBREF_URL}">
                    <c:set var="isSaved" value="${true}"/>
                </c:if>
            </c:forEach>
            <c:if test="${isSaved eq false}">
                <c:url var="savelink" value="DispatcherServlet">
                    <c:param name="action" value="save"/>
                    <c:param name="playerId" value="${param.playerId}"/>
                    <c:param name="link" value="${requestScope.FBREF_URL}"/>
                </c:url>
                <a href="${savelink}">Save player information</a>
            </c:if>
            <c:if test="${isSaved eq true}">
                <c:url value="DispatcherServlet" var="removeLink">
                    <c:param name="action" value="remove"/>
                    <c:param name="link" value="${requestScope.FBREF_URL}"/>
                </c:url>
                <a href="${removeLink}">Remove from saved list</a>
            </c:if>

            <a href="DispatcherServlet?action=logout">Logout</a>
        </c:if>
        <c:if test="${empty sessionScope.USERNAME}">
            <a href="login.html">Login</a>
            <a href="register.jsp">Register</a>
        </c:if>

        <c:set var="xsl" value="${applicationScope.XSL_PLAYER_DETAIL}"/>
        <c:set var="xml" value="${requestScope.XML_PLAYER_DETAIL}"/>
        <c:if test="${not empty xml and not empty xsl}">
            <x:transform xml="${xml}" xslt="${xsl}"/>
        </c:if>

        <c:set var="cosine_distances" value="${requestScope.COSINE_DISTANCES}"/>
        <c:set var="cosine_arr" value=""/>
        <c:forEach items="${cosine_distances}" var="i">
            <c:set var="cosine_arr" value="${cosine_arr}${'['}${i[0]}${','}${i[1]}${']'}${','}"/>
        </c:forEach>


        <c:set var="euclidean_distances" value="${requestScope.EUCLIDEAN_DISTANCES}"/>
        <c:set var="euclidean_arr" value=""/>
        <c:forEach items="${euclidean_distances}" var="i">
            <c:set var="euclidean_arr" value="${euclidean_arr}${'['}${i[0]}${','}${i[1]}${']'}${','}"/>
        </c:forEach>

        <c:set var="playersStats" value="${requestScope.XML_PLAYERS_STATS}"/>
        <c:set var="quantity" value="${requestScope.RECOMMEND_QUANTITY}"/>
        <c:set var="similarPlayers" value="${requestScope.XML_SIMILAR_PLAYERS}"/>

        <div id="cosine-table"></div>
        <div id="euclidean-table"></div>
        <button onclick="printStatsTables()">Compare</button>
        <button onclick="clearTable()">Clear</button>
        <div id="standard-table"></div>
        <div id="shooting-table"></div>
        <div id="passing-table"></div>
        <div id="misc-table"></div>

        <script type="text/javascript">
            /*<![CDATA[*/
            let subjectId = ${param.playerId};
            let cosine = ${'['} ${cosine_arr} ${']'};
                    let euclidean = ${'['}${euclidean_arr}${']'};
            let xmlStats = '${fn:replace(playersStats, "'", '&apos;')}';
            let xmlInfos = '${fn:replace(similarPlayers, "'", '&apos;')}';
            let quantity = ${quantity};

            let parser = new DOMParser();
            let statsDom = parser.parseFromString(xmlStats, 'text/xml');
            let infosDom = parser.parseFromString(xmlInfos, 'text/xml');
            printCosineTable('cosine-table', cosine, 'Cosine similarity');
            printCosineTable('euclidean-table', euclidean, 'Euclidean distance');
            function clearTable() {
                let table = document.getElementById('standard-table');
                table.innerHTML = '';
                table = document.getElementById('shooting-table');
                table.innerHTML = '';
                table = document.getElementById('passing-table');
                table.innerHTML = '';
                table = document.getElementById('misc-table');
                table.innerHTML = '';
            }
            function printStatsTables() {
                let idsToCompare = new Set(getListIdToCompare())
                printStandardTable(idsToCompare)
                printShootingTable(idsToCompare)
                printPassingTable(idsToCompare)
                printMiscTable(idsToCompare)
            }
            function getListIdToCompare() {
                let idsToCompare = [subjectId]
                let inputs = document.getElementsByTagName('input');
                for (input of inputs) {
                    if (input.name === 'compare' && input.checked === true) {
                        idsToCompare.push(input.value)
                    }
                }
                return idsToCompare
            }
            function printMiscTable(idsToCompare) {
                let miscTableDiv = document.getElementById('misc-table')
                let headers = [
                    'cards_yellow_red', 'fouls', 'fouled', 'offsides', 'crosses',
                    'tackles_won', 'interceptions', 'pens_won', 'pens_conceded',
                    'own_goals', 'dribbles_completed', 'dribbles',
                    'dribbles_completed_pct', 'players_dribbled_past',
                    'nutmegs', 'dribble_tackles', 'dribbles_vs',
                    'dribble_tackles_pct', 'dribbled_past'
                ]
                let i = 1
                let rows = []
                for (id of idsToCompare) {
                    let row = getMiscRowData(id, i, headers)
                    i++
                    rows.push(row)
                }
                headers = ['No.', 'Name', ].concat(headers);
                miscTableDiv.innerHTML = '<h2>Miscelaneous Stats</h2>' + makeTableHtml(headers, rows)
            }
            function printPassingTable(idsToCompare) {
                let passingTableDiv = document.getElementById('passing-table')
                let headers = [
                    'xa_net', 'assisted_shots', 'passes_completed', 'passes',
                    'passes_pct', 'passes_completed_short', 'passes_short',
                    'passes_pct_short', 'passes_completed_medium', 'passes_medium',
                    'passes_pct_medium', 'passes_completed_long', 'passes_long',
                    'passes_pct_long', 'passes_left_foot', 'passes_right_foot',
                    'passes_free_kicks', 'through_balls', 'corner_kicks', 'throw_ins',
                    'passes_into_final_third', 'passes_into_penalty_area',
                    'crosses_into_penalty_area'
                ]
                let i = 1
                let rows = []
                for (id of idsToCompare) {
                    let row = getPassingRowData(id, i, headers)
                    i++
                    rows.push(row)
                }
                headers = ['No.', 'Name', ].concat(headers);
                passingTableDiv.innerHTML = '<h2>Passing Stats</h2>' + makeTableHtml(headers, rows)
            }
            function printShootingTable(idsToCompare) {
                let shootingTableDiv = document.getElementById('shooting-table')
                let headers = [
                    'pens_made', 'pens_att', 'shots_total', 'shots_on_target',
                    'shots_free_kicks', 'shots_on_target_pct', 'shots_total_per90',
                    'shots_on_target_per90', 'goals_per_shot', 'goals_per_shot_on_target',
                    'xg', 'npxg', 'npxg_per_shot', 'xg_net'
                ]
                let i = 1
                let rows = []
                for (id of idsToCompare) {
                    let row = getShootingRowData(id, i, headers)
                    i++
                    rows.push(row)
                }
                headers = ['No.', 'Name', ].concat(headers);
                shootingTableDiv.innerHTML = '<h2>Shooting Stats</h2>' + makeTableHtml(headers, rows)
            }
            function printStandardTable(idsToCompare) {
                let standardTableDiv = document.getElementById('standard-table')
                let headers = [
                    'No.', 'Name',
                    'seasons', 'squad', 'comp_level', 'games', 'minutes',
                    'goals', 'assists', 'cards_yellow', 'cards_red',
                    'goals_per90', 'assists_per90', 'goals_assists_per90', 'goals_assists_pens_per90',
                    'xa', 'xg_per90', 'xa_per90', 'xg_xa_per90', 'npxg_per90', 'npxg_xa_per90'
                ]
                let i = 1
                let rows = []
                for (id of idsToCompare) {
                    let row = getStandardRowData(id, i)
                    i++
                    rows.push(row)
                }
                standardTableDiv.innerHTML = '<h2>Standard Stats</h2>' + makeTableHtml(headers, rows)
            }
            function getMiscRowData(id, index, headers) {
                return getShootingRowData(id, index, headers)
            }
            function getPassingRowData(id, index, headers) {
                return getShootingRowData(id, index, headers)
            }
            function getShootingRowData(id, index, headers) {
                let node = getPlayerStatsNode(id)
                let nodeName = getPlayerInfoNode(id)

                let row = '<tr>'
                row += '<td>' + index + '</td>'
                row += '<td>' + nodeName.getElementsByTagName('name')[0].innerHTML + '</td>'

                for (header of headers) {
                    console.log(header)
                    row += '<td>' + Number(node.getElementsByTagName(header)[0].innerHTML).toFixed(2) + '</td>'
                }

                row += '</tr>'
                return row
            }
            function getStandardRowData(id, index) {
                let node = getPlayerStatsNode(id)
                let nodeName = getPlayerInfoNode(id)

                let row = '<tr>'
                row += '<td>' + index + '</td>'
                row += '<td>' + nodeName.getElementsByTagName('name')[0].innerHTML + '</td>'
                row += '<td>' + node.getElementsByTagName('seasons')[0].innerHTML + '</td>'
                row += '<td>' + node.getElementsByTagName('squad')[0].innerHTML + '</td>'
                row += '<td>' + node.getElementsByTagName('comp_level')[0].innerHTML + '</td>'
                row += '<td>' + node.getElementsByTagName('games')[0].innerHTML + '</td>'
                row += '<td>' + node.getElementsByTagName('minutes')[0].innerHTML + '</td>'
                row += '<td>' + node.getElementsByTagName('goals')[0].innerHTML + '</td>'
                row += '<td>' + node.getElementsByTagName('assists')[0].innerHTML + '</td>'
                row += '<td>' + node.getElementsByTagName('cards_yellow')[0].innerHTML + '</td>'
                row += '<td>' + node.getElementsByTagName('cards_red')[0].innerHTML + '</td>'
                row += '<td>' + Number(node.getElementsByTagName('goals_per90')[0].innerHTML).toFixed(2) + '</td>'
                row += '<td>' + Number(node.getElementsByTagName('assists_per90')[0].innerHTML).toFixed(2) + '</td>'
                row += '<td>' + Number(node.getElementsByTagName('goals_assists_per90')[0].innerHTML).toFixed(2) + '</td>'
                row += '<td>' + Number(node.getElementsByTagName('goals_assists_pens_per90')[0].innerHTML).toFixed(2) + '</td>'
                row += '<td>' + Number(node.getElementsByTagName('xa')[0].innerHTML).toFixed(2) + '</td>'
                row += '<td>' + Number(node.getElementsByTagName('xg_per90')[0].innerHTML).toFixed(2) + '</td>'
                row += '<td>' + Number(node.getElementsByTagName('xa_per90')[0].innerHTML).toFixed(2) + '</td>'
                row += '<td>' + Number(node.getElementsByTagName('xg_xa_per90')[0].innerHTML).toFixed(2) + '</td>'
                row += '<td>' + Number(node.getElementsByTagName('npxg_per90')[0].innerHTML).toFixed(2) + '</td>'
                row += '<td>' + Number(node.getElementsByTagName('npxg_xa_per90')[0].innerHTML).toFixed(2) + '</td>'
                row += '</tr>'
                return row
            }
            function makeTableHtml(headers, rows) {
                let table = '<table><thead><tr>'
                // header
                for (header of headers) {
                    table += '<th>' + header + '</th>'
                }
                table += '</tr></thead><tbody>'
                // body
                for (row of rows) {
                    table += row
                }
                table += '</tbody></body>'
                return table
            }
            function printCosineTable(id, ids_distances, tableName) {
                let cosineTableDiv = document.getElementById(id)
                let headers = ['No.', 'Point', 'Name', 'value', 'Club', 'Compare']
                let bodyRows = []
                let i = 1;
                for (item of ids_distances) {
                    let row = getCosineRowData(item[0], i, item[1])
                    i++;
                    bodyRows.push(row)
                }
                cosineTableDiv.innerHTML = '<h3>' + tableName + '</h3>' + makeTableHtml(headers, bodyRows)
            }
            function getCosineRowData(id, index, point) {
                let node = getPlayerInfoNode(id)
                let checkbox = "<input type='checkbox' name='compare' value='" + id + "'>"
                let url = 'DispatcherServlet?action=viewPlayerProfile&playerId=' + id
                let link = "<a href='" + url + "'>" + node.getElementsByTagName('name')[0].innerHTML + "</a>"
                let row = '<tr>'
                row += '<td>' + index + '</td>'
                row += '<td>' + point + '</td>'
                row += '<td>' + link + '</td>'
                row +=
                        '<td>' +
                        Number(node.getElementsByTagName('value')[0].innerHTML).toFixed(2) +
                        node.getElementsByTagName('unit')[0].innerHTML +
                        '</td>'
                row += '<td>' + node.getElementsByTagName('club')[0].innerHTML + '</td>'
                row += '<td>' + checkbox + '</td>'
                row += '</tr>'
                return row
            }

            function getPlayerInfoNode(idNum) {
                let ids = infosDom.getElementsByTagName('id');
                for (id of ids) {
                    if (id.textContent == idNum)
                        return id.parentNode
                }
            }
            function getPlayerStatsNode(idNum) {
                let ids = statsDom.getElementsByTagName('id');
                for (id of ids)
                    if (id.textContent == idNum)
                        return id.parentNode
            }
            /*]]>*/
        </script>


    </body>
</html>
