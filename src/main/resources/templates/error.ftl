<!DOCTYPE html>
<html lang="en">
<body>
<#--Something wrong: ${status} ${error}-->
<#if status == 401>
    <#include "./401.html">
<#elseif status == 404>
    <#include "./404.html">
<#else>
    <#include "./500.html">
</#if>
</body>
</html>