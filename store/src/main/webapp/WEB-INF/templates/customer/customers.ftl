<!DOCTYPE html>
<html>
<head>
    <title>Пользователи</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/table.css">
    <link rel="shortcut icon" href="/resources/static/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/static/favicon.ico" type="image/x-icon">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.js"></script>
</head>
<body>
<#include "../parts/header.ftl"/>
<#import "../parts/pager.ftl" as p>
<main class="container">
    <h2>Список пользователей (${page.getTotalElements()})</h2>
    <form class="adding-wrap" action="/admin/customers/add" method="POST">
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
            <input class="form-control ${(emailError??)?string('is-invalid', '')}"
                   value="<#if user??>${user.email}</#if>"
                   name="email" type="text" placeholder="some@some.com"/>
            <#if emailError??>
                <div class="invalid-feedback">
                    ${emailError}
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
        <div class="input-wrap">
            <input class="form-control ${(passwordError??)?string('is-invalid', '')}"
                   name="password" type="password" placeholder="Пароль"/>
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>
        <div class="input-wrap">
            <input class="add-button" style="margin-top: 0" type="submit" value="Добавить"/>
        </div>
    </form>
    <#if page.getContent()?has_content>
        <div class="table">
            <table>
                <thead>
                <tr class="table-head">
                    <th class="column">ID</th>
                    <th class="column">Имя</th>
                    <th class="column">Фамилия</th>
                    <th class="column">Телефон</th>
                    <th class="column">E-mail</th>
                    <th class="column"></th>
                </tr>
                </thead>
                <tbody>
                <#list page.getContent() as user>
                    <tr>
                        <td class="column">${user.userId}</td>
                        <td class="column">${user.name}</td>
                        <td class="column">${user.surname}</td>
                        <td class="column">${user.phone}</td>
                        <td class="column">${user.email}</td>
                        <td class="column">
                            <ul class="actions">
                                <li>
                                    <button><a href="/admin/orders/customer/${user.userId}">Заказы</a></button>
                                </li>
                                <li>
                                    <a href="/admin/customers/update/${user.userId}"><button id="upd-btn"></button></a>
                                </li>
                                <li>
                                    <a href="/admin/customers/delete/${user.userId}"><button id="dlt-btn"></button></a>
                                </li>
                            </ul>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <@p.pager url page/>
    <#else>
        <h1 class="message">Нет пользователей. Вернуться на <a href="/" style="color: #009272">главную</a>.</h1>
    </#if>
</main>
<script src="/resources/js/main.js"></script>
</body>
</html>