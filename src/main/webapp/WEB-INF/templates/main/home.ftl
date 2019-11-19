<!DOCTYPE html>
<html>
<head>
    <title>Каталог</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/home.css">
    <link rel="shortcut icon" href="/resources/static/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/static/favicon.ico" type="image/x-icon">
</head>
<body>
<#include "../parts/header.ftl"/>
<#import "../parts/pager.ftl" as p>
<main class="container">
    <div class="products">
        <div class="product-list-filter">
            <div class="nav-title">Каталог</div>
            <nav class="catalog">
                <ul>
                    <#if categories?has_content>
                        <#list categories as category>
                            <#if (category.getSubCategories())?has_content>
                                <li><a href="/catalog/${category.getCategoryId()}">${category.getCategoryName()}</a>
                                    <ul class="sub-catalog">
                                        <#list category.getSubCategories() as subcat>
                                            <li><a href="/catalog/${subcat.getCategoryId()}">${subcat.getCategoryName()}</a></li>
                                        </#list>
                                    </ul>
                                </li>
                            <#elseif !((category.getParent())??)>
                                <li><a href="/catalog/${category.getCategoryId()}">${category.getCategoryName()}</a></li>
                            </#if>
                        </#list>
                    </#if>
                </ul>
            </nav>
        </div>
        <div class="product-list-content">
            <div class="product-list">
                <#if page.content?has_content>
                    <#list page.content as product>
                        <div class="product-list-item">
                            <a href="/catalog/book/${product.productId}">
                                <div class="product-img">
                                    <img src="/img/${product.filename}" alt="prod-img">
                                </div>
                            </a>
                            <div class="product-info">
                                <a class="product-info-header" href="/catalog/book/${product.productId}">
                                    <div class="product-title">
                                        ${product.title}
                                    </div>
                                    <div class="product-author">
                                        ${product.author}
                                    </div>
                                </a>
                                <div class="product-info-footer">
                                    <div class="product-price">
                                        ${product.price} ₽
                                    </div>
                                    <@sec.authorize access="!hasAuthority('ADMIN')">
                                    <a href="/buyProduct?productId=${product.productId}">
                                        <button class="product-button">Купить</button>
                                    </a>
                                    </@sec.authorize>
                                    <@sec.authorize access="hasAuthority('ADMIN')">
                                        <a href="/admin/products/update?productId=${product.productId}">
                                            <button class="product-button">Изменить</button>
                                        </a>
                                    </@sec.authorize>
                                </div>
                            </div>
                        </div>
                    </#list>
                    <#else>
                    <h1 class="message">Нет книг.</h1>
                </#if>
            </div>
            <@p.pager url page/>
        </div>
    </div>
</main>
</body>
</html>
