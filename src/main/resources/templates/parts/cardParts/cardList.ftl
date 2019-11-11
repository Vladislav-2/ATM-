<@p.pager url page/>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">CardUser</th>
        <th scope="col">CardNumber</th>
        <th scope="col">CreatedDate</th>
        <th scope="col">UpdatedDate</th>
        <th scope="col">UsageTime</th>
        <th scope="col">Cash</th>
        <th scope="col">isBlocked</th>
        <th scope="col">is Deleted</th>
        <th scope="col">To Block</th>
        <th scope="col">Remove and Reaper</th>
    </tr>
    </thead>
    <tbody>
     <#list page.content as card>

     <tr>
            <#if card.id??>
                <th scope="row">${card.id}</th></#if>
            <#if card.cardUser??>
                <td>${card.cardUser}</td></#if>
            <#if card.cardNumder??>
                <td>${card.cardNumder}</td></#if>
            <#if card.createdDate??>
                <td>${card.createdDate}</td></#if>
            <#if card.updatedDate??>
                <td>${card.updatedDate}</td></#if>
            <#if card.usageTime??>
                <td>${card.usageTime}</td></#if>
            <#if card.cash??>
                <td>${card.cash}</td></#if>
            <#if card.isBlocked??>
                <td>${card.isBlocked?then("true","false")}</td></#if>
            <#if card.deleted??>
                <td>${card.deleted?then("true","false")}</td></#if>

         <td>${card.deleted?then(
         '<div href="card?reaperCard=${card.id}"  class="btn btn-secondary">edit</div>',
         '<a href="card?editCard=${card.id}"  class="btn btn-info">edit</a></td>')
         }

         <td>${card.deleted?then(
         '<a href="card?reaperCard=${card.id}"  class="btn btn-outline-danger">reaper</a>',
         '<a href="card?removeCard=${card.id}"  class="btn btn-outline-danger">remove</a>')
         }
         </td>

     </tr>
     <#else>
        <td>Cars lst is Empty</td>

     </#list>
    </tbody>
</table>
<@p.pager url page/>