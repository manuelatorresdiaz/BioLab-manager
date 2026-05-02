<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">

<html>
<head>
    <title>BioLab Report</title>
    <style>
        body { font-family: Arial; }
        table { border-collapse: collapse; width: 60%; margin-bottom: 20px; }
        th, td { border: 1px solid black; padding: 8px; text-align: left; }
        th { background-color: #ddd; }
        h2 { margin-top: 30px; }
    </style>
</head>

<body>

<h1>BioLab Database Report</h1>

<!-- USERS TABLE -->
<h2>Users</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Password</th>
    </tr>

    <xsl:for-each select="BioLabDatabase/Users/User">
        <tr>
            <td><xsl:value-of select="@id"/></td>
            <td><xsl:value-of select="username"/></td>
            <td><xsl:value-of select="password"/></td>
        </tr>
    </xsl:for-each>

</table>

<!-- ROLES TABLE -->
<h2>Roles</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Role Name</th>
    </tr>

    <xsl:for-each select="BioLabDatabase/Roles/Role">
        <tr>
            <td><xsl:value-of select="@id"/></td>
            <td><xsl:value-of select="roleName"/></td>
        </tr>
    </xsl:for-each>

</table>

</body>
</html>

</xsl:template>

</xsl:stylesheet>