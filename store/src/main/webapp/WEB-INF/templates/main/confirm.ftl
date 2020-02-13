<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <title>Подтверждение заказа</title>
</head>
<body>
<#include "../parts/header.ftl"/>
<main class="container">
    <h1 class="message">Ваш заказ номер ${order.getCartId()} на сумму ${order.getTotalPrice()} ₽ принят!</h1>
    <p style="text-align: center">Благодарим за покупку! Вернуться в <a href="/" style="color: #009272">магазин</a>.</p>
</main>
</body>
</html>