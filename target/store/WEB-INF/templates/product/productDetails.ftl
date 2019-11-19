<!DOCTYPE html>
<html>
<head>
    <title>${productDetails.title}</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/product.css">
    <link rel="shortcut icon" href="/resources/static/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/static/favicon.ico" type="image/x-icon">
</head>
<body>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]>
<#include "../parts/header.ftl"/>
<main class="container">
    <div class="wrapper">
        <div class="product-details">
            <div class="product-left">
                <div class="product-header">
                    <h1 class="product-title">${productDetails.title}</h1>
                </div>
                <div class="product-description">
                    ${productDetails.description}
                </div>
            </div>
            <div class="product-right">
                <div class="product-image">
                    <img src="/img/${productDetails.filename}" alt="img">
                </div>
                <div class="product-properties">
                    <div class="product-prop">
                        <div class="product-prop-title">
                            ID товара
                        </div>
                        <div class="product-prop-value">
                            ${productDetails.productId}
                        </div>
                    </div>
                    <div class="product-prop">
                        <div class="product-prop-title">
                            Автор
                        </div>
                        <div class="product-prop-value">
                            ${productDetails.author}
                        </div>
                    </div>
                    <div class="product-prop">
                        <div class="product-prop-title">
                            Жанр
                        </div>
                        <div class="product-prop-value">
                            ${productDetails.getCategory().getCategoryName()}
                        </div>
                    </div>
                    <div class="product-prop">
                        <div class="product-prop-title">
                            Год издания
                        </div>
                        <div class="product-prop-value">
                            ${productDetails.year}
                        </div>
                    </div>
                    <div class="trade-container">
                        <div class="product-price">${productDetails.price} ₽</div>
                        <@sec.authorize access="!hasAuthority('ADMIN')">
                            <a href="/buyProduct?productId=${productDetails.productId}">
                                <button class="product-button" style="width: 120px;">Купить</button>
                            </a>
                        </@sec.authorize>
                        <@sec.authorize access="hasAuthority('ADMIN')">
                            <div style="display: inline-flex; white-space: pre-line">
                                <a href="/admin/products/update?productId=${productDetails.productId}">
                                    <button class="product-button">Изменить</button>
                                </a>
                                <a href="/admin/products/delete?productId=${productDetails.productId}">
                                    <button class="delete-btn"></button>
                                </a>
                            </div>
                        </@sec.authorize>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>