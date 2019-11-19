<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]>
<@sec.authorize access="!isAuthenticated()">
    <#assign ref="/sign_in" str="Войти">
</@sec.authorize>
<@sec.authorize access="isAuthenticated()">
    <#assign ref="/logout" str="Выйти">
</@sec.authorize>

<header>
    <div class="header-menu">
        <div class="container">
            <div class="logo-container">
                <a style="padding-top: 10px;" href="/catalog">
                    <div id="logotype">
                        <span>
                            <img src="/resources/static/books-logo.png" alt="logo">
                        </span>
                        <span id="logo-name">КНИГА ГИКА</span>
                    </div>
                </a>
                <a href="${ref}">
                    <button class="login-logo-container">
                        <span style="margin-right: 5px;">
                            <img src="/resources/static/logo-login.png" alt="login">
                        </span>
                        <span style="color: white;">${str}</span>
                    </button>
                </a>
            </div>
        </div>
    </div>
    <div class="menu-container">
        <nav class="menu">
            <ul>
                <@sec.authorize access="hasAuthority('ADMIN')">
                    <li><a href="/admin/customers">Панель управления</a>
                        <ul class="submenu">
                            <li><a href="/admin/customers">Пользователи</a></li>
                            <li><a href="/admin/products">Товары</a></li>
                            <li><a href="/admin/orders">Заказы</a></li>
                        </ul>
                    </li>
                </@sec.authorize>
                <li><a href="/catalog">Книги</a></li>
                <@sec.authorize access="!hasAuthority('ADMIN')">
                    <li><a href="/cart">Корзина</a></li>
                </@sec.authorize>
                <@sec.authorize access="hasAuthority('USER')">
                    <li><a href="/orders">Мои заказы</a></li>
                    <li><a href="/account">Мой аккаунт</a></li>
                </@sec.authorize>
            </ul>
        </nav>
    </div>
</header>