<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : playerTable.xsl
    Created on : March 21, 2020, 10:03 AM
    Author     : KHANHBQSE63463
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <xsl:element name="section">
            <xsl:attribute name="class">
                <xsl:text>grid-1</xsl:text>
            </xsl:attribute>
            <xsl:for-each select="//player_infos/player_info">
                
                <xsl:variable name="id" select="id"/>
                <xsl:variable name="transfermarkt_avartar" select="transfermarkt_avartar"/>
                <xsl:variable name="fbref_avartar" select="fbref_avartar"/>
                <xsl:variable name="transfermarkt_url" select="transfermarkt_url"/>
                <xsl:variable name="fbref_url" select="fbref_url"/>
                <xsl:variable name="club" select="club"/>
                <xsl:variable name="nation" select="nation"/>
                <xsl:variable name="place_of_birth" select="place_of_birth"/>
                <xsl:variable name="name" select="name"/>
                <xsl:variable name="birth_date" select="birth_date"/>
                <xsl:variable name="height" select="height"/>
                <xsl:variable name="contact_expires" select="contact_expires"/>
                <xsl:variable name="value" select="current_value/value"/>
                <xsl:variable name="unit" select="current_value/unit"/>
                <xsl:variable name="lasted_update" select="current_value/lasted_update"/>
                <xsl:element name="div">
                    <xsl:element name="img">
                        <xsl:attribute name="src">
                            <xsl:if test="$fbref_avartar != ''">
                                <xsl:value-of select="$fbref_avartar"/>
                            </xsl:if>
                            <xsl:if test="$fbref_avartar = ''">
                                <xsl:value-of select="$transfermarkt_avartar"/>
                            </xsl:if>
                        </xsl:attribute>
                    </xsl:element>
                    <xsl:element name="h3">
                        <xsl:element name="a">
                            <xsl:attribute name="href">
                                <xsl:text>DispatcherServlet?action=viewPlayerProfile&amp;playerId=</xsl:text>
                                <xsl:value-of select="$id"/>
                            </xsl:attribute>
                            <xsl:value-of select="$name"/>
                        </xsl:element>
                    </xsl:element>
                    <xsl:element name="p">
                        <xsl:text>Club: </xsl:text> 
                        <xsl:value-of select="$club"/>
                    </xsl:element>
                    <xsl:element name="p">
                        <xsl:text>Value: </xsl:text>
                        <xsl:if test="$value != 0">
                            <xsl:value-of select="floor($value*100) div 100"/>
                            <xsl:value-of select="$unit"/>
                    
                        </xsl:if>
                        <xsl:if test="$value = 0">
                            <xsl:text>Unknow</xsl:text>
                        </xsl:if>
                    </xsl:element>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
