<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]>
<!DOCTYPE html>
<html>
<head>
    <title>Заказы</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/table.css">
    <link rel="shortcut icon" href="/resources/static/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/static/favicon.ico" type="image/x-icon">
    <style>
        .block span, .block label {
            font-weight: bold;
            margin-left: 10px;
        }

        .block {
            display: flex;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<#include "../parts/header.ftl"/>
<main class="container">
    <#if order?has_content>
        <#assign address = order.getShippingAddress()>
        <h2>Заказ №${order.getOrderId()}</h2>
        <div class="block">
            Адрес доставки: <span>${address.country}, ${address.city}, ${address.street}</span>
        </div>
        <div class="block">
            Статус заказа: <@sec.authorize access="hasAuthority('USER')">
                <span>${order.getOrderStatus()}</span></@sec.authorize>
            <@sec.authorize access="hasAuthority('ADMIN')">
                <form modelattribute="order"
                      action="/admin/orders/edit" method="post">
                    <label>
                        <select name="orderStatus" onchange="this.form.submit()">
                            <#list status as key, value>
                                <option value="${key}"
                                        <#if order?? && order.getOrderStatus() == value>selected</#if>>${value}</option>
                            </#list>
                        </select>
                    </label>
                </form>
            </@sec.authorize>
        </div>
        <div class="table">
            <table>
                <thead>
                <tr class="table-head">
                    <th class="column">ID</th>
                    <th class="column">Название книги</th>
                    <th class="column">Автор</th>
                    <th class="column">Количество в заказе</th>
                    <th class="column">Стоимость за ед.</th>
                    <th class="column">Подытог</th>
                </tr>
                </thead>
                <tbody>
                <#list order.getOrderedProducts() as item>
                    <tr>
                        <td class="column">${item.product.productId}</td>
                        <td class="column">${item.product.title}</td>
                        <td class="column">${item.product.author}</td>
                        <td class="column">${item.getQuantity()}</td>
                        <td class="column">${item.product.price} ₽</td>
                        <td class="column">${item.getTotalPrice()} ₽</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <div id="order-price">
            Стоимость заказа: <span>${order.getTotalOrderPrice()} ₽</span>
        </div>
    <#else>
        <h1 class="message">Заказ не найден. Вернуться к <a href="/admin/orders" style="color: #009272">заказам</a>.
        </h1>
    </#if>
</main>
</body>
</html>