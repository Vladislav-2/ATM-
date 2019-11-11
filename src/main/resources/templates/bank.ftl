<#import "parts/pageTemplate.ftl" as pt>

<@pt.page>

    <#import "parts/pager.ftl" as p>

    <@p.pager url page/>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">BankName</th>
        <th scope="col">GlobalBalance</th>
        <th scope="col">is Deleted</th>
        <th scope="col">Remove/Reaper</th>
    </tr>
    </thead>
    <tbody>
     <#list page.content as bank>

     <tr>
            <#if bank.id??>
                <th scope="row">${bank.id}</th></#if>
            <#if bank.bankName??>
                <td>${bank.bankName}</td></#if>
            <#if bank.globalBalance??>
                <td>${bank.globalBalance}</td></#if>
            <#if bank.deleted??>
                <td>${bank.deleted?then("true","false")}</td></#if>

     <td>${bank.deleted?then(
     '<a href="bank?reaper=${bank.id}"  class="btn btn-outline-danger">reaper</a>',
     '<a href="bank?remove=${bank.id}"  class="btn btn-outline-danger">remove</a>')
     }
     </td>


     </tr>
     <#else>
        <td colspan="5" style="text-align:center">List of banks is Empty</td>

     </#list>
    </tbody>
</table>
    <@p.pager url page/>

<h3>Edit bank</h3>
    <#if savingReport??><div class="alert alert-danger" role="alert">${savingReport}</div></#if>

<div class="form-group mt-3">
    <form method="post">

        <input type="hidden" name="id" value="<#if bank?? && bank.id??>${bank.id}</#if>">

        <div class="form-group">
            <input type="text" name="bankName"
                   class="form-control ${(bankNameError??)?string('is-invalid', '')}"
                   value="" placeholder="Enter the name of bank">
            <#if bankNameError??>
                <div class="invalid-feedback">
                    ${bankNameError}
                </div>
            </#if>
        </div>
        <div class="form-group">
            <input type="number" name="globalBalance"
                   class="form-control ${(globalBalanceError??)?string('is-invalid', '')}"
                   value="" placeholder="Enter the cash of bank">
            <#if globalBalanceError??>
                <div class="invalid-feedback">
                    ${globalBalanceError}
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