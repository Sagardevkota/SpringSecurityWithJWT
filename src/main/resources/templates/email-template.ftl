<!DOCTYPE html>
<html>
<head>
    <style>
        body{
            background: #dddddd ;
        }
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            margin-left: auto;
            margin-right: auto;


        }
        img {
            display: block;
            margin-left: auto;
            margin-right: auto;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        th{
            background-color: #f44336;
            color: white;
        }

        tr:nth-child(even) {
            background-color: white;
        }
    </style>
</head>
<body>

<h1 style="text-align: center; color:#f44336 ">S-Mart</h1>


<p style="font-family: Arial, Helvetica, sans-serif; text-align: center;">Dear ${userName} , <br> Your order has been received and is in waiting state.<br>Our representatives will call you soon to confirm the order.</p>

<h2 style="text-align: center;">Order Details</h2>

<table>
    <tr>
        <th>Product Name</th>
        <th>Qty</th>
        <th>Product Size</th>
        <th>Product Color</th>
        <th>Delivery Address</th>
        <th>Price</th>
    </tr>
    <tr>
        <td> ${productName} </td>
        <td> ${qty} </td>
        <td> ${size} </td>
        <td> ${color} </td>
        <td> ${address} </td>
        <td style="color: #f44336"> Rs. ${price} </td>
    </tr>

</table>
</div>

</body>
</html>
