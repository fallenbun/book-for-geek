<#macro pager url page>
    <#assign totalPages = page.getTotalPages() pageNumber = page.getNumber()>
    <#if totalPages gt 0>
        <div role="navigation">
            <ul class="pagination custom-buttons">
                <li class="nav-button">
                    <a href="<#if pageNumber gt 0>${url}?page=${pageNumber - 1}<#else></#if>">Prev</a>
                </li>
                <#list 1..totalPages as p>
                    <#if (p - 1) == pageNumber>
                        <li class="page-item active"><a href="${url}?page=${p - 1}">${p}</a></li>
                    <#else>
                        <li class="page-item"><a href="${url}?page=${p - 1}">${p}</a></li>
                    </#if>
                </#list>
                <li class="nav-button">
                    <a href="<#if pageNumber lt (totalPages - 1)>${url}?page=${pageNumber + 1}<#else></#if>">Next</a>
                </li>
                <#--            <li class="page-item"><span>...</span></li>-->
            </ul>
        </div>
    </#if>
</#macro>