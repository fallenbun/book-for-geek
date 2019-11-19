<!DOCTYPE html>
<html lang="en">
<head>
    <title>Заказы</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/table.css">
    <link rel="shortcut icon" href="/resources/static/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/static/favicon.ico" type="image/x-icon">
</head>
<body>

<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]>
<@sec.authorize access="hasAuthority('ADMIN')">
    <#assign refer="/admin">
</@sec.authorize>
<@sec.authorize access="!hasAuthority('ADMIN')">
    <#assign refer="">
</@sec.authorize>
<#import "../parts/pager.ftl" as p>
<#include "../parts/header.ftl"/>
<main class="container">
    <#if page.getContent()?has_content>
        <#if user??>
            <h2>Мои заказы (${page.getTotalElements()})</h2>
        <#else>
            <h2>Список заказов (${page.getTotalElements()})</h2>
        </#if>
        <div class="table">
            <table>
                <thead>
                <tr class="table-head">
                    <th class="column">ID заказа</th>
                    <th class="column">Дата создания</th>
                    <#if user??>
                    <#else>
                        <th class="column">Покупатель</th>
                    </#if>
                    <th class="column">Количество продуктов</th>
                    <th class="column">Стоимость заказа</th>
                    <th class="column">Статус заказа</th>
                    <th class="column"></th>
                </tr>
                </thead>
                <tbody>
                <#list page.getContent() as order>
                    <tr>
                        <td class="column">${order.orderId}</td>
                        <td class="column">${order.dateCreated}</td>
                        <#if user??>
                        <#else>
                            <td class="column">${order.user.name} ${order.user.surname}</td>
                        </#if>
                        <td class="column">${order.numberOfProducts}</td>
                        <td class="column">${order.getTotalOrderPrice()} ₽</td>
                        <td class="column">${order.orderStatus}</td>
                        <td class="column">
                            <ul class="actions">
                                <@sec.authorize access="!hasAuthority('ADMIN')">
                                    <li>
                                        <button>
                                            <a href="/orders/order?orderId=${order.orderId}">Подробнее</a>
                                        </button>
                                    </li>
                                </@sec.authorize>
                                <@sec.authorize access="hasAuthority('ADMIN')">
                                    <li>
                                        <a href="/admin/orders/update?orderId=${order.orderId}">
                                            <button id="upd-btn"></button>
                                        </a>
                                    </li>
                                </@sec.authorize>
                                <li>
                                    <a href="${refer}/orders/delete?orderId=${order.orderId}">
                                        <button id="dlt-btn"></button>
                                    </a>
                                </li>
                            </ul>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    <#else>
        <#if user??>
            <h1 class="message">Нет заказов. Вернуться на <a href="/" style="color: #009272">главную</a>.</h1>
        <#else>
            <h1 class="message">Нет заказов. Вернуться к <a href="/admin/customers" style="color: #009272">списку</a>.
            </h1>
        </#if>
    </#if>
    <@p.pager url page/>
</main>
</body>
</html>