<!DOCTYPE html>
<html>
<head>
    <title>Добавление нового товара</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="shortcut icon" href="/resources/static/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/static/favicon.ico" type="image/x-icon">
</head>
<body>
<#include "../parts/header.ftl"/>
<main class="container">
    <form modelattribute="product" class="update-wrap"
          enctype="multipart/form-data" action="/admin/products/add" method="POST">
        <h3>Добавить новый товар</h3>
        <div class="input-wrap">
            <input class="form-control ${(titleError??)?string('is-invalid', '')}"
                   value="<#if product??>${product.title}</#if>"
                   name="title" type="text" placeholder="Название"/>
            <#if titleError??>
                <div class="invalid-feedback">
                    ${titleError}
                </div>
            </#if>
        </div>
        <div class="input-wrap">
            <input class="form-control ${(authorError??)?string('is-invalid', '')}"
                   value="<#if product??>${product.author}</#if>"
                   name="author" type="text" placeholder="Автор"/>
            <#if authorError??>
                <div class="invalid-feedback">
                    ${authorError}
                </div>
            </#if>
        </div>
        <div class="input-wrap">
            <select class="form-control ${(categoryError??)?string('is-invalid', '')}" name="category">
                <option value="0">--Выберите категорию--</option>
                <#if categories?has_content>
                    <#list categories as category>
                        <option
                                <#if product?? && product.category?? &&
                                product.category.getCategoryId() == category.getCategoryId()>
                                    selected
                                </#if>
                                value="${category.getCategoryId()}">
                            ${category.getCategoryName()}
                        </option>
                    </#list>
                </#if>
            </select>
            <#if categoryError??>
                <div class="invalid-feedback">
                    ${categoryError}
                </div>
            </#if>
        </div>
        <div class="input-wrap">
            <input class="form-control ${(yearError??)?string('is-invalid', '')}"
                   value="<#if product??>${product.year}</#if>"
                   name="year" type="text" placeholder="Год издания"/>
            <#if yearError??>
                <div class="invalid-feedback">
                    ${yearError}
                </div>
            </#if>
        </div>
        <div class="input-wrap">
            <input class="form-control ${(priceError??)?string('is-invalid', '')}"
                   value="<#if product?? && product.getPrice()??>${product.getPrice()}</#if>"
                   name="price" type="number" min="0" placeholder="Стоимость"/>
            <#if priceError??>
                <div class="invalid-feedback">
                    ${priceError}
                </div>
            </#if>
        </div>
        <div class="input-wrap">
            <label>
                <textarea class="form-control ${(descriptionError??)?string('is-invalid', '')}"
                          style="height: 125px; resize: none" name="description" placeholder="Описание"><#if
                    product??>${product.description}</#if></textarea>
            </label>
            <#if descriptionError??>
                <div class="invalid-feedback">
                    ${descriptionError}
                </div>
            </#if>
        </div>
        <div class="input-wrap">
            <label style="margin-bottom: 5px; font-size: 14px; display:inline-block;">Доступные форматы: jpg,
                png</label>
            <input class="form-control ${(filenameError??)?string('is-invalid', '')}"
                   name="file" type="file" accept="image/jpeg,image/png">
            <#if filenameError??>
                <div class="invalid-feedback">
                    ${filenameError}
                </div>
            </#if>
        </div>
        <div class="input-wrap">
            <input class="add-button" type="submit" value="Добавить">
        </div>
    </form>
</main>
</body>
</html>