<!DOCTYPE html>
<html xmlns:form="http://www.w3.org/1999/html">
<head>
    <title>Оформление заказа</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/checkout.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.js"></script>
    <link rel="shortcut icon" href="/resources/static/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/static/favicon.ico" type="image/x-icon">
</head>
<body>
<#include "../parts/header.ftl"/>
<main class="container">
    <form class="checkout" href="/checkout" method="post">
        <div style="display: flex;">
            <div class="customer-data block">
                <div class="title wrapper">Данные покупателя</div>
                <div class="content wrapper">
                    <div class="input-wrap">
                        <input class="form-control ${(nameError??)?string('is-invalid', '')}"
                               value="<#if user??>${user.name}</#if>"
                               name="name" type="text" placeholder="Имя"/>
                        <#if nameError??>
                            <div class="invalid-feedback">
                                ${nameError}
                            </div>
                        </#if>
                    </div>
                    <div class="input-wrap">
                        <input class="form-control ${(surnameError??)?string('is-invalid', '')}"
                               value="<#if user??>${user.surname}</#if>"
                               name="surname" type="text" placeholder="Фамилия"/>
                        <#if surnameError??>
                            <div class="invalid-feedback">
                                ${surnameError}
                            </div>
                        </#if>
                    </div>
                    <div class="input-wrap">
                        <input class="form-control ${(phoneError??)?string('is-invalid', '')}"
                               value="<#if user??>${user.phone}</#if>"
                               id="phone" name="phone" type="text" placeholder="Телефон"/>
                        <#if phoneError??>
                            <div class="invalid-feedback">
                                ${phoneError}
                            </div>
                        </#if>
                    </div>
                </div>
            </div>
            <div class="address block">
                <div class="title wrapper">Адрес доставки</div>
                <div class="content wrapper">
                    <div class="input-wrap">
                        <select class="form-control ${(countryError??)?string('is-invalid', '')}" name="country">
                            <option value="none">--Выберите страну--</option>
                            <#list countries as key, value>
                                <option value="${value}" <#if address?? && address.country?? && address.country == value>selected</#if>>${value}</option>
                            </#list>
                        </select>
                        <#if countryError??>
                            <div class="invalid-feedback">
                                ${countryError}
                            </div>
                        </#if>
                    </div>
                    <div class="input-wrap">
                        <input class="form-control ${(cityError??)?string('is-invalid', '')}"
                               value="<#if address?? && address.city??>${address.city}</#if>"
                               type="text" name="city" placeholder="Город">
                        <#if cityError??>
                            <div class="invalid-feedback">
                                ${cityError}
                            </div>
                        </#if>
                    </div>
                    <div style="width: 100%">
                        <input class="form-control ${(streetError??)?string('is-invalid', '')}"
                               value="<#if address?? && address.street??>${address.street}</#if>"
                               type="text" name="street" placeholder="Улица">
                        <#if streetError??>
                            <div class="invalid-feedback">
                                ${streetError}
                            </div>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
        <input type="submit" class="buy-button" value="Подтвердить">
    </form>
</main>
<script src="/resources/js/main.js"></script>
</body>
</html>