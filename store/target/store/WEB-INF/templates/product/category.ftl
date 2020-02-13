<!DOCTYPE html>
<html>
<head>
    <title>Категории</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="shortcut icon" href="/resources/static/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/static/favicon.ico" type="image/x-icon">
    <style>
        #category-list {
            display: block;
            width: auto;
        }

        .category-item {
            display: flex;
        }

        .input-wrap {
            width: auto;
            margin-right: 30px;
        }

        .input-wrap:last-of-type {
            margin-right: 0;
        }
    </style>
</head>
<body>
<#include "../parts/header.ftl"/>
<main class="container">
        <h2>Добавить категорию</h2>
        <form action="/admin/category/add" method="POST">
            <div class="category-item">
                <div class="input-wrap">
                    <input class="form-control ${(categoryNameError??)?string('is-invalid', '')}"
                           value="<#if category??>${category.categoryName}</#if>"
                           name="categoryName" type="text" placeholder="Название"/>
                    <#if categoryNameError??>
                        <div class="invalid-feedback">
                            ${categoryNameError}
                        </div>
                    </#if>
                </div>
                <#if categories?has_content>
                    <div class="input-wrap">
                        <select class="form-control" name="parent">
                            <option value="0">--Выберите категорию--</option>
                            <#if categories?has_content>
                                <#list categories as category>
                                    <option value="${category.getCategoryId()}">
                                        ${category.getCategoryName()}
                                    </option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </#if>
                <div class="input-wrap">
                    <input class="add-button" style="margin-top: 0" type="submit" value="Добавить"/>
                </div>
            </div>
        </form>

        <h2>Изменить категорию</h2>
        <#list categories as category>
            <form action="/admin/category/edit" method="POST">
                <div id="category-list">
                    <div class="category-item">
                        <div class="input-wrap">
                            <input class="form-control ${(categoryNameError??)?string('is-invalid', '')}"
                                   value="<#if category??>${category.categoryName}</#if>"
                                   name="categoryName" type="text" placeholder="Название"/>
                            <#if categoryNameError??>
                                <div class="invalid-feedback">
                                    ${categoryNameError}
                                </div>
                            </#if>
                        </div>
                        <#if categories?has_content>
                            <div class="input-wrap">
                                <select class="form-control" name="parent">
                                    <option value="0">--Выберите категорию--</option>
                                    <#list categories as parent>
                                        <#if category.getParent()?? &&
                                        parent.getCategoryId() == category.getParent().getCategoryId()>
                                            <option selected value="${category.getParent().getCategoryId()}">
                                                ${category.getParent().getCategoryName()}
                                            </option>
                                        <#else>
                                            <option value="${parent.getCategoryId()}">
                                                ${parent.getCategoryName()}
                                            </option>
                                        </#if>
                                    </#list>
                                </select>
                            </div>
                        </#if>
                        <div class="input-wrap">
                            <input class="add-button" style="margin-top: 0" type="submit" value="Обновить"/>
                        </div>
                        <div class="input-wrap">
                            <a href="/admin/category/delete?categoryId=${category.categoryId}">
                                <div class="delete-btn"></div>
                            </a>
                        </div>
                    </div>
                </div>
            </form>
        </#list>
</main>
</body>
</html>