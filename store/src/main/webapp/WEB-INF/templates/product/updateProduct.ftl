<!DOCTYPE html>
<html>
<head>
    <title>Редактировать данные о товаре</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/product.css">
    <link rel="shortcut icon" href="/resources/static/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/static/favicon.ico" type="image/x-icon">
</head>
<body>
<#include "../parts/header.ftl"/>
<main class="container">
    <#if editProduct??>
    <div class="wrapper">
        <form modelattribute="editProduct" class="product-details" enctype="multipart/form-data"
              action="/admin/products/update" method="post">
            <div class="product-left">
                <div class="product-header">
                    <input class="form-control product-title ${(titleError??)?string('is-invalid', '')}"
                           name="title" type="text"
                           value="<#if editProduct?has_content>${editProduct.title}</#if>">
                    <#if titleError??>
                        <div class="invalid-feedback">
                            ${titleError}
                        </div>
                    </#if>
                </div>
                <div class="product-description">
                <textarea class="form-control ${(descriptionError??)?string('is-invalid', '')}" name="description"
                          style="height: 250px; line-height: 1.7em; resize: none"><#if
                    editProduct?has_content>${editProduct.description}</#if></textarea>
                    <#if descriptionError??>
                        <div class="invalid-feedback">
                            ${descriptionError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="product-right">
                <div class="product-image">
                    <img src="/img/<#if editProduct?has_content && editProduct.filename??>${editProduct.filename}</#if>" alt="img">
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
                </div>
                <div class="product-properties">
                    <div class="product-prop">
                        <div class="product-prop-title">
                            ID товара
                        </div>
                        <div class="block-wrap">
                            <input class="form-control product-prop-value"
                                   name="productId" type="text"
                                   value="${editProduct.productId}" readonly>
                        </div>
                    </div>
                    <div class="product-prop">
                        <div class="product-prop-title">
                            Автор
                        </div>
                        <div class="block-wrap">
                            <input class="form-control product-prop-value ${(authorError??)?string('is-invalid', '')}"
                                   name="author" type="text"
                                   value="${editProduct.author}">
                            <#if authorError??>
                                <div class="invalid-feedback">
                                    ${authorError}
                                </div>
                            </#if>
                        </div>
                    </div>
                    <div class="product-prop">
                        <div class="product-prop-title">
                            Жанр
                        </div>
                        <div class="block-wrap">
                            <select class="form-control ${(categoryError??)?string('is-invalid', '')}" name="category">
                                <option value="0">--Выберите категорию--</option>
                                <#if categories?has_content>
                                    <#list categories as category>
                                        <option
                                                <#if editProduct?has_content && editProduct.category?? &&
                                                editProduct.category.getCategoryId() == category.getCategoryId()>
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
                    </div>
                    <div class="product-prop">
                        <div class="product-prop-title">
                            Год издания
                        </div>
                        <div class="block-wrap">
                            <input class="form-control ${(yearError??)?string('is-invalid', '')}"
                                   value="<#if editProduct?has_content>${editProduct.year}</#if>"
                                   name="year" type="text" placeholder="Год издания"/>
                            <#if yearError??>
                                <div class="invalid-feedback">
                                    ${yearError}
                                </div>
                            </#if>
                        </div>
                    </div>
                    <div class="product-prop">
                        <div class="product-prop-title">
                            Стоимость
                        </div>
                        <div class="block-wrap">
                            <input class="form-control ${(priceError??)?string('is-invalid', '')}"
                                   value="<#if editProduct?has_content && editProduct.getPrice()??>${editProduct.getPrice()}</#if>"
                                   name="price" type="number" min="0" placeholder="Стоимость"/>
                            <#if priceError??>
                                <div class="invalid-feedback">
                                    ${priceError}
                                </div>
                            </#if>
                        </div>
                    </div>
                    <input type="submit" class="product-button" value="Сохранить">
                </div>
            </div>
        </form>
    </div>
        <#else>
            <h1 class="message">Продукт не найден.</h1>
    </#if>
</main>
</body>
</html>