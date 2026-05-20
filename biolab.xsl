<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

<xsl:template match="/">

<html>

<head>

    <title>BioLab Report</title>

    <style>

        body {
            font-family: Arial;
            margin: 30px;
        }

        table {
            border-collapse: collapse;
            width: 90%;
            margin-bottom: 30px;
        }

        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #dddddd;
        }

        h1 {
            color: #003366;
        }

        h2 {
            margin-top: 40px;
            color: #005599;
        }

    </style>

</head>

<body>

<h1>BioLab Database Report</h1>

<h2>Users</h2>

<table>

    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Password</th>
    </tr>

    <xsl:for-each select="BioLabDatabase/Users/User">

        <tr>
            <td><xsl:value-of select="id"/></td>
            <td><xsl:value-of select="username"/></td>
            <td><xsl:value-of select="password"/></td>
        </tr>

    </xsl:for-each>

</table>

<h2>Roles</h2>

<table>

    <tr>
        <th>ID</th>
        <th>Role Name</th>
    </tr>

    <xsl:for-each select="BioLabDatabase/Roles/Role">

        <tr>
            <td><xsl:value-of select="id"/></td>
            <td><xsl:value-of select="roleName"/></td>
        </tr>

    </xsl:for-each>

</table>

<h2>Patients</h2>

<table>

    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Date Of Birth</th>
        <th>Gender</th>
        <th>Phone</th>
        <th>Address</th>
    </tr>

    <xsl:for-each select="BioLabDatabase/Patients/Patient">

        <tr>
            <td><xsl:value-of select="patientId"/></td>
            <td><xsl:value-of select="firstName"/></td>
            <td><xsl:value-of select="lastName"/></td>
            <td><xsl:value-of select="dateOfBirth"/></td>
            <td><xsl:value-of select="gender"/></td>
            <td><xsl:value-of select="phone"/></td>
            <td><xsl:value-of select="address"/></td>
        </tr>

    </xsl:for-each>

</table>

<h2>Physicians</h2>

<table>

    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Specialty</th>
        <th>Phone</th>
        <th>Email</th>
    </tr>

    <xsl:for-each select="BioLabDatabase/Physicians/Physician">

        <tr>
            <td><xsl:value-of select="physicianId"/></td>
            <td><xsl:value-of select="firstName"/></td>
            <td><xsl:value-of select="lastName"/></td>
            <td><xsl:value-of select="specialty"/></td>
            <td><xsl:value-of select="phone"/></td>
            <td><xsl:value-of select="email"/></td>
        </tr>

    </xsl:for-each>

</table>

<h2>Laboratory Orders</h2>

<table>

    <tr>
        <th>Order ID</th>
        <th>Order Date</th>
        <th>Status</th>
    </tr>

    <xsl:for-each select="BioLabDatabase/LaboratoryOrders/LaboratoryOrder">

        <tr>
            <td><xsl:value-of select="orderId"/></td>
            <td><xsl:value-of select="orderDate"/></td>
            <td><xsl:value-of select="status"/></td>
        </tr>

    </xsl:for-each>

</table>

<h2>Tests</h2>

<table>

    <tr>
        <th>Test ID</th>
        <th>Test Name</th>
        <th>Unit</th>
        <th>Description</th>
    </tr>

    <xsl:for-each select="BioLabDatabase/Tests/Test">

        <tr>
            <td><xsl:value-of select="testId"/></td>
            <td><xsl:value-of select="testName"/></td>
            <td><xsl:value-of select="unit"/></td>
            <td><xsl:value-of select="description"/></td>
        </tr>

    </xsl:for-each>

</table>

<h2>Order Tests</h2>

<table>

    <tr>
        <th>Order ID</th>
        <th>Test ID</th>
        <th>Result Value</th>
        <th>Result Date</th>
        <th>Status</th>
    </tr>

    <xsl:for-each select="BioLabDatabase/OrderTests/OrderTest">

        <tr>
            <td><xsl:value-of select="orderId"/></td>
            <td><xsl:value-of select="testId"/></td>
            <td><xsl:value-of select="resultValue"/></td>
            <td><xsl:value-of select="resultDate"/></td>
            <td><xsl:value-of select="resultStatus"/></td>
        </tr>

    </xsl:for-each>

</table>

<h2>Reference Ranges</h2>

<table>

    <tr>
        <th>ID</th>
        <th>Min Value</th>
        <th>Max Value</th>
        <th>Critical Min</th>
        <th>Critical Max</th>
        <th>Units</th>
    </tr>

    <xsl:for-each select="BioLabDatabase/ReferenceRanges/ReferenceRange">

        <tr>
            <td><xsl:value-of select="referenceId"/></td>
            <td><xsl:value-of select="minValue"/></td>
            <td><xsl:value-of select="maxValue"/></td>
            <td><xsl:value-of select="criticalMin"/></td>
            <td><xsl:value-of select="criticalMax"/></td>
            <td><xsl:value-of select="units"/></td>
        </tr>

    </xsl:for-each>

</table>

</body>

</html>

</xsl:template>

</xsl:stylesheet>