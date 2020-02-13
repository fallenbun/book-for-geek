<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>Корзина</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/cart.css">
    <link rel="shortcut icon" href="/resources/static/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/static/favicon.ico" type="image/x-icon">
</head>
<body>
<#include "../parts/header.ftl"/>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]>
<main class="container">
    <#if cart.cartItems?has_content>
        <div class="basket-wrap">
            <div class="basket">
                <a href="/cart/clear" class="clear-basket">Очистить корзину</a>
                <div class="basket-labels">
                    <ul>
                        <li class="item item-heading">Товар</li>
                        <li class="price">Стоимость</li>
                        <li class="quantity">Количество</li>
                        <li class="subtotal">Подытог</li>
                    </ul>
                </div>
                <#list cart.cartItems as item>
                    <div class="basket-product">
                        <div class="item">
                            <div class="product-image">
                                <img alt="book" src="/img/${item.product.filename}"
                                     class="product-frame">
                            </div>
                            <div class="product-details">
                                <div class="item-title">${item.product.title}</div>
                                <div class="item-author" style="color:#777777">${item.product.author}</div>
                            </div>
                        </div>
                        <div class="price">${item.product.price}</div>
                        <form action="/cart/item/update?productId=${item.product.productId}"
                              method="post">
                            <div class="quantity">
                                <input name="quantity" type="number" value="${item.quantity}" min="1"
                                       class="quantity-field" onchange="this.form.submit()">
                            </div>
                        </form>
                        <div class="subtotal">${item.product.price * item.quantity}</div>
                        <div class="remove">
                            <a href="/cart/item/delete?productId=${item.product.productId}">
                                <button>Удалить</button>
                            </a>
                        </div>
                    </div>
                </#list>
            </div>
            <aside>
                <div class="summary">
                    <div class="summary-total-items">
                        Ваша корзина: <span class="total-items">${cart.getTotalItems()}</span>
                    </div>
                    <div class="summary-total">
                        <div class="total-title">Итого</div>
                        <div class="total-value final-value" id="basket-total"
                             style="display: block;">${cart.totalPrice}</div>
                    </div>
                    <div class="summary-checkout">
                        <@sec.authorize access="isAuthenticated()">
                            <a href="/checkout">
                                <button class="buy-button">Оформить заказ</button>
                            </a>
                        </@sec.authorize>
                        <@sec.authorize access="!isAuthenticated()">
                            <a href="/sign_in">
                                <button class="buy-button">Войти для оформления</button>
                            </a>
                        </@sec.authorize>
                    </div>
                    <div class="back-to-store">
                        <a href="/catalog">Продолжить выбор</a>
                    </div>
                </div>
            </aside>
        </div>
    <#else>
        <h1 class="message">Ваша корзина пуста. Вернуться в <a href="/" style="color: #009272">магазин</a>.</h1>
    </#if>
</main>
</body>
</html>