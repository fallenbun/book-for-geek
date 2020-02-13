<!DOCTYPE html>
<html>
<head>
    <title>Редактирование данных пользователя</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="shortcut icon" href="/resources/static/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/static/favicon.ico" type="image/x-icon">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.js"></script>
</head>
<body>
<#include "../parts/header.ftl"/>
<main class="container">
        <#if updateUser??>
            <form class="update-wrap" modelattribute="updateUser" action="/admin/customers/update" method="post">
                <div class="input-wrap">
                    <h3>Редактировать данные пользователя</h3>
                    <#if success??>
                        <p style="color: #00bd20; font-size: 14px; text-align: start">Данные успешно обновлены!</p>
                    </#if>
                    <input class="form-control ${(nameError??)?string('is-invalid', '')}"
                           value="<#if updateUser??>${updateUser.name}</#if>"
                           name="name" type="text" placeholder="Имя"/>
                    <#if nameError??>
                        <div class="invalid-feedback">
                            ${nameError}
                        </div>
                    </#if>
                </div>
                <div class="input-wrap">
                    <input class="form-control ${(surnameError??)?string('is-invalid', '')}"
                           value="<#if updateUser??>${updateUser.surname}</#if>"
                           name="surname" type="text" placeholder="Фамилия"/>
                    <#if surnameError??>
                        <div class="invalid-feedback">
                            ${surnameError}
                        </div>
                    </#if>
                </div>
                <div class="input-wrap">
                    <input class="form-control ${(emailError??)?string('is-invalid', '')}"
                           value="<#if updateUser??>${updateUser.email}</#if>"
                           name="email" type="text" placeholder="some@some.com"/>
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
                <div class="input-wrap">
                    <input class="form-control ${(phoneError??)?string('is-invalid', '')}"
                           value="<#if updateUser??>${updateUser.phone}</#if>"
                           id="phone" name="phone" type="text" placeholder="Телефон"/>
                    <#if phoneError??>
                        <div class="invalid-feedback">
                            ${phoneError}
                        </div>
                    </#if>
                </div>
                <input class="add-button" type="submit" value="Обновить"/>
            </form>
        <#else>
            <h1 class="message">Пользователь не найден.</h1>
        </#if>
</main>
<script src="/resources/js/main.js"></script>
</body>
</html>