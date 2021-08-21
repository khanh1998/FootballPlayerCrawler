<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : playerDetail.xsl
    Created on : March 21, 2020, 6:52 PM
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
        <xsl:variable name="id" select="//id"/>
        <xsl:variable name="transfermarkt_avartar" select="//transfermarkt_avartar"/>
        <xsl:variable name="fbref_avartar" select="//fbref_avartar"/>
        <xsl:variable name="transfermarkt_url" select="//transfermarkt_url"/>
        <xsl:variable name="fbref_url" select="//fbref_url"/>
        <xsl:variable name="club" select="//club"/>
        <xsl:variable name="nation" select="//nation"/>
        <xsl:variable name="place_of_birth" select="//place_of_birth"/>
        <xsl:variable name="name" select="//name"/>
        <xsl:variable name="birth_date" select="//birth_date"/>
        <xsl:variable name="height" select="//height"/>
        <xsl:variable name="contact_expires" select="//contact_expires"/>
        <xsl:variable name="value" select="//current_value/value"/>
        <xsl:variable name="unit" select="//current_value/unit"/>
        <xsl:variable name="lasted_update" select="//current_value/lasted_update"/>
        
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
                <xsl:value-of select="$name"/>
            </xsl:element>
            <xsl:element name="p">
                <xsl:text>Club: </xsl:text> 
                <xsl:value-of select="$club"/>
            </xsl:element>
            <xsl:if test="$height != 0">
                <xsl:element name="p">
                    <xsl:text>Height: </xsl:text>
                    <xsl:value-of select="ceiling($height * 100)"/>
                    <xsl:text>cm </xsl:text>
                </xsl:element>
            </xsl:if>
            <xsl:if test="$nation != ''">
                <xsl:element name="p">
                    <xsl:text>Nation: </xsl:text>
                    <xsl:value-of select="$nation"/>
                </xsl:element>
            </xsl:if>
            <xsl:if test="$place_of_birth != ''">
                <xsl:element name="p">
                    <xsl:text>Place of birth: </xsl:text>
                    <xsl:value-of select="$place_of_birth"/>
                </xsl:element>
            </xsl:if>
            <xsl:if test="$birth_date != ''">
                <xsl:element name="p">
                    <xsl:text>Date of birth: </xsl:text>
                    <xsl:value-of select="$birth_date"/>
                </xsl:element>
            </xsl:if>
            <xsl:element name="p">
                <xsl:text>Value: </xsl:text>
                <xsl:if test="$value != 0">
                    <xsl:value-of select="floor($value*100) div 100"/>
                    <xsl:value-of select="$unit"/>
                    <xsl:text> (</xsl:text>
                    <xsl:value-of select="$lasted_update"/>
                    <xsl:text>)</xsl:text>
                </xsl:if>
                <xsl:if test="$value = 0">
                    <xsl:text>Unknow</xsl:text>
                </xsl:if>
            </xsl:element>
            <xsl:if test="$contact_expires != ''">
                <xsl:element name="p">
                    <xsl:text>Contact expires: </xsl:text>
                    <xsl:value-of select="$contact_expires"/>
                </xsl:element>
            </xsl:if>
            <xsl:element name="h3">
                <xsl:element name="a">
                    <xsl:attribute name="href">
                        <xsl:value-of select="$fbref_url"/>
                    </xsl:attribute>
                    <xsl:text>FBRef</xsl:text>
                </xsl:element>
            </xsl:element>
            <xsl:if test="$transfermarkt_url != ''">
                <xsl:element name="h3">
                    <xsl:element name="a">
                        <xsl:attribute name="href">
                            <xsl:value-of select="$transfermarkt_url"/>
                        </xsl:attribute>
                        <xsl:text>Transfermarkt</xsl:text>
                    </xsl:element>
                </xsl:element>
            </xsl:if>
            
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
