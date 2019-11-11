<#import "parts/pageTemplate.ftl" as pt>

<@pt.page>

    <#import "parts/pager.ftl" as p>

    <@p.pager url page/>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">BankId</th>
        <th scope="col">BankCardId</th>
        <th scope="col">BankUserId</th>
        <th scope="col">Balance</th>
        <th scope="col">is Deleted</th>
        <th scope="col">Remove/Reaper</th>
    </tr>
    </thead>
    <tbody>
     <#list page.content as account>

     <tr>
            <#if account.id??>
                <th scope="row">${account.id}</th></#if>
            <#if account.bankId??>
                <td>${account.bankId}</td></#if>
            <#if account.bankCardId??>
                <td>${account.bankCardId}</td></#if>
         <#if account.bankUserId??>
                <td>${account.bankUserId}</td></#if>
         <#if account.balance??>
                <td>${account.balance}</td></#if>
            <#if account.deleted??>
                <td>${account.deleted?then("true","false")}</td></#if>

     <#--<td>${bank.isBlocked()?then(-->
     <#--'<a href="bank?unBlock=${bank.id}"  class="btn btn-outline-warning">block</a>',-->
     <#--'<a href="bank?block=${bank.id}"  class="btn btn-outline-warning">block</a>')-->
     <#--}-->
     <#--</td>-->

     <#--<td>${bank.deleted?then(-->
     <#--'<a href="bank?reaper=${bank.id}"  class="btn btn-outline-danger">reaper</a>',-->
     <#--'<a href="bank?remove=${bank.id}"  class="btn btn-outline-danger">remove</a>')-->
     <#--}-->
     <#--</td>-->


     </tr>
     <#else>
        <td colspan="5" style="text-align:center">List of accounts is Empty</td>

     </#list>
    </tbody>
</table>
    <@p.pager url page/>

<h3>Edit account</h3>
    <#if savingReport??><div class="alert alert-danger" role="alert">${savingReport}</div></#if>

<div class="form-group mt-3">
    <form method="post">

        <input type="hidden" name="id" value="<#if account?? && account.id??>${account.id}</#if>">

        <div class="form-group">
            <input type="number" name="bankId"
                   class="form-control ${(bankIdError??)?string('is-invalid', '')}"
                   value="" placeholder="Enter the id of bank">
            <#if bankIdError??>
                <div class="invalid-feedback">
                    ${bankIdError}
                </div>
            </#if>
        </div>
        <div class="form-group">
            <input type="number" name="bankCardId"
                   class="form-control ${(bankCardIdError??)?string('is-invalid', '')}"
                   value="" placeholder="Enter the id of card">
            <#if bankCardIdError??>
                <div class="invalid-feedback">
                    ${bankCardIdError}
                </div>
            </#if>
        </div>
        <div class="form-group">
            <input type="number" name="bankUserId"
                   class="form-control ${(bankUserIdError??)?string('is-invalid', '')}"
                   value="" placeholder="Enter the id of user">
            <#if bankUserIdError??>
                <div class="invalid-feedback">
                    ${bankUserIdError}
                </div>
            </#if>
        </div>
        <div class="form-group">
            <input type="number" name="balance"
                   class="form-control ${(balanceError??)?string('is-invalid', '')}"
                   value="" placeholder="Enter the balance of account">
            <#if balanceError??>
                <div class="invalid-feedback">
                    ${balanceError}
                </div>
            </#if>
        </div>
</div>

        <div class="form-group mt-2">
            <button type="submit" class="btn btn-primary">
                Create
            </button>
        </div>
    </form>
</div>

</@pt.page>