<#import "parts/pageTemplate.ftl" as pt>

<@pt.page>

    <#import "parts/pager.ftl" as p>

    <@p.pager url page/>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">BankId</th>
        <th scope="col">CardId</th>
        <th scope="col">Cash</th>
        <th scope="col">MaxCash</th>
        <th scope="col">isInseredCard</th>
        <th scope="col">isBlocked</th>
        <th scope="col">is Deleted</th>
        <th scope="col">Remove/Reaper</th>
    </tr>
    </thead>
    <tbody>
     <#list page.content as atm>

     <tr>
            <#if atm.id??>
                <th scope="row">${atm.id}</th></#if>
            <#if atm.bankId??>
                <td>${atm.bankId}</td></#if>
            <#if atm.cardId??>
                <td>${atm.cardId}</td></#if>
            <#if atm.cash??>
                <td>${atm.cash}</td></#if>
            <#if atm.maxCash??>
                <td>${atm.maxCash}</td></#if>
            <#if atm.isInseredCard??>
                <td>${atm.isInseredCard?then("true","false")}</td></#if>
            <#if atm.isBlocked??>
                <td>${atm.isBlockedString()}</td></#if>
            <#if atm.deleted??>
                <td>${atm.deleted?then("true","false")}</td></#if>

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
        <td colspan="5" style="text-align:center">ATM list is Empty</td>

     </#list>
    </tbody>
</table>
    <@p.pager url page/>

<h3>Edit ATM</h3>
    <#if savingReport??><div class="alert alert-danger" role="alert">${savingReport}</div></#if>

<div class="form-group mt-3">
    <form method="post">

        <input type="hidden" name="id" value="<#if atm?? && atm.id??>${atm.id}</#if>">

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
            <input type="number" name="cash"
                   class="form-control ${(cashError??)?string('is-invalid', '')}"
                   value="" placeholder="Enter the cash of ATM">
            <#if cashError??>
                <div class="invalid-feedback">
                    ${cashError}
                </div>
            </#if>
        </div>
        <div class="form-group">
            <input type="number" name="maxCash"
                   class="form-control ${(maxCashError??)?string('is-invalid', '')}"
                   value="" placeholder="Enter the max of cash in ATM">
                <#if maxCashError??>
                    <div class="invalid-feedback">
                        ${maxCashError}
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