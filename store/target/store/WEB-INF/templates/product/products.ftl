<!DOCTYPE html>
<html>
<head>
    <title>Список товаров</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/table.css">
    <link rel="shortcut icon" href="/resources/static/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/static/favicon.ico" type="image/x-icon">
    <style>
        div a:hover {
            color: #aa0101;
        }
    </style>
</head>
<body>
<#include "../parts/header.ftl"/>
<#import "../parts/pager.ftl" as p>
<main class="container">

    <h2>Список товаров (${page.getTotalElements()})</h2>
    <div style="margin-bottom: 10px">
        <a href="/admin/products/add">Добавить товар</a>
        <a href="/admin/categories">Категории</a>
    </div>
    <#if page.getContent()?has_content>
        <table class="table">
            <thead>
            <tr class="table-head">
                <th class="column">ID</th>
                <th class="column">Название книги</th>
                <th class="column">Автор</th>
                <th class="column">Жанр</th>
                <th class="column">Год издания</th>
                <th class="column">Стоимость</th>
                <th class="column"></th>
            </tr>
            </thead>
            <tbody>
            <#list page.getContent() as product>
                <tr>
                    <td class="column">${product.productId}</td>
                    <td class="column">${product.title}</td>
                    <td class="column">${product.author}</td>
                    <td class="column">${product.category.getCategoryName()}</td>
                    <td class="column">${product.year}</td>
                    <td class="column">${product.price} ₽</td>
                    <td class="column">
                        <a href="/admin/products/update?productId=${product.productId}">
                            <button id="upd-btn"></button>
                        </a>
                        <a href="/admin/products/delete?productId=${product.productId}">
                            <button id="dlt-btn"></button>
                        </a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
        <@p.pager url page/>
    <#else>
        <h1 class="message">Список пуст.</h1>
    </#if>
</main>
</body>
</html>