<#import "parts/pageTemplate.ftl" as pt>

<@pt.page>

    <#import "parts/pager.ftl" as p>

    <@p.pager url page/>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">FirstName</th>
        <th scope="col">Surname</th>
        <th scope="col">PatronymicName</th>
        <th scope="col">Email</th>
        <th scope="col">Description</th>
        <th scope="col">is Deleted</th>
        <th scope="col">Remove/Reaper</th>
    </tr>
    </thead>
    <tbody>
     <#list page.content as bankUser>

     <tr>
         <#if bankUser.id??>
                <th scope="row">${bankUser.id}</th></#if>
         <#if bankUser.firstName??>
                <td>${bankUser.firstName}</td></#if>
         <#if bankUser.surname??>
                <td>${bankUser.surname}</td></#if>
         <#if bankUser.patronymicName??>
                <td>${bankUser.patronymicName}</td></#if>
         <#if bankUser.email??>
                <td>${bankUser.email}</td></#if>
         <#if bankUser.description??>
                <td>${bankUser.description}</td></#if>
         <#if bankUser.deleted??>
                <td>${bankUser.deleted?then("true","false")}</td></#if>

     <td>${bankUser.deleted?then(
     '<a href="bankUser?reaper=${bankUser.id}"  class="btn btn-outline-danger">reaper</a>',
     '<a href="bankUser?remove=${bankUser.id}"  class="btn btn-outline-danger">remove</a>')
     }
     </td>


     </tr>
     <#else>
        <td colspan="5" style="text-align:center">List of bank user is Empty</td>

     </#list>
    </tbody>
</table>
    <@p.pager url page/>

<h3>Edit bank user</h3>
    <#if savingReport??><div class="alert alert-danger" role="alert">${savingReport}</div></#if>

<div class="form-group mt-3">
    <form method="post">

        <input type="hidden" name="id" value="<#if bankUser?? && bankUser.id??>${bankUser.id}</#if>">

        <div class="form-group">
            <input type="text" name="firstName"
                   class="form-control ${(firstNameError??)?string('is-invalid', '')}"
                   value="" placeholder="Enter the first name of user">
            <#if firstNameError??>
                <div class="invalid-feedback">
                    ${firstNameError}
                </div>
            </#if>
        </div>
        <div class="form-group">
            <input type="text" name="surname"
                   class="form-control ${(surnameError??)?string('is-invalid', '')}"
                   value="" placeholder="Enter the surname of user">
            <#if surnameError??>
                <div class="invalid-feedback">
                    ${surnameError}
                </div>
            </#if>
        </div>
        <div class="form-group">
            <input type="text" name="patronymicName"
                   class="form-control ${(patronymicNameError??)?string('is-invalid', '')}"
                   value="" placeholder="Enter the patronymic name of user">
            <#if patronymicNameError??>
                <div class="invalid-feedback">
                    ${patronymicNameError}
                </div>
            </#if>
        </div>
        <div class="form-group">
            <input type="text" name="email"
                   class="form-control ${(emailError??)?string('is-invalid', '')}"
                   value="" placeholder="Enter the email of user">
            <#if emailError??>
                <div class="invalid-feedback">
                    ${emailError}
                </div>
            </#if>
        </div>
        <div class="form-group">
            <input type="text" name="description"
                   class="form-control ${(descriptionError??)?string('is-invalid', '')}"
                   value="" placeholder="Enter the description of user">
            <#if descriptionError??>
                <div class="invalid-feedback">
                    ${descriptionError}
                </div>
            </#if>
        </div>
</div>

        <div class="form-group mt-2">
            <button type="submit" class="btn btn-primary">
                Create
            </button>
            </form>
        </div>
</div>

</@pt.page>