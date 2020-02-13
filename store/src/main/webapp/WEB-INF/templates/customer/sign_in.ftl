<!DOCTYPE html>
<html>
<head>
    <title>Вход</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/login.css">
    <link rel="shortcut icon" href="/resources/static/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/static/favicon.ico" type="image/x-icon">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.js"></script>
</head>
<body>
<#include "../parts/header.ftl"/>
<div class="login-page">
    <div class="form">
        <form class="login-form" action="/j_spring_security_check" method="post"
              <#if user??>style="display: none"</#if>>
            <div class="form-content">
                <h1>Войти</h1>
                <#if error??>
                    <p class="invalid-feedback" style="position: relative">Некорректный email или пароль!</p>
                </#if>
                <div class="input-wrap">
                    <input class="form-control" id="username"
                           name="j_username" placeholder="Email" type="text"/>
                </div>
                <div class="input-wrap">
                    <input class="form-control" id="password"
                           name="j_password" placeholder="Пароль" type="password"/>
                </div>
                <input class="add-button" type="submit" value="Войти"/>
                <p class="message">Еще нет аккаунта? <a>Зарегистрироваться</a></p>
            </div>
        </form>
        <form class="register-form" action="/sign_up" method="post" <#if user??>style="display: block"</#if>>
            <div class="form-content">
                <h1>Регистрация</h1>
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
                    <div class="flag-dropdown">
                        <select id="country">
                            <#list countries as key, value>
                                <option value="${key}">${key}</option>
                            </#list>
                        </select>
                    </div>
                    <input class="form-control ${(phoneError??)?string('is-invalid', '')}"
                           style="padding-left: 75px"
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
                    <input class="form-control ${(password2Error??)?string('is-invalid', '')}"
                           name="password2" type="password" placeholder="Повторите пароль"/>
                    <#if password2Error??>
                        <div class="invalid-feedback">
                            ${password2Error}
                        </div>
                    </#if>
                </div>
                <input class="add-button" type="submit" value="Зарегистрироваться"/>
                <p class="message">Уже зарегистрированы? <a>Войти</a></p>
            </div>
        </form>
    </div>
</div>
<script>
    $('.message a').click(function () {
        $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
    });
    $(function () {
        function maskPhone() {
            let country = $('#country option:selected').val();
            switch (country) {
                case "RU":
                    $("#phone").mask("+7(999) 999-99-99");
                    break;
                case "UA":
                    $("#phone").mask("+380(999) 999-99-99");
                    break;
                case "BY":
                    $("#phone").mask("+375(999) 999-99-99");
                    break;
                case "KZ":
                    $("#phone").mask("+7(999) 999-99-99");
                    break;
            }
        }

        maskPhone();
        $('#country').change(function () {
            maskPhone();
        });
    });
</script>
</body>
</html>