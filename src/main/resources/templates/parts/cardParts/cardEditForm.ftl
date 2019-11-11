<h3>Edit card</h3>
<#if savingReport??><div class="alert alert-danger" role="alert">${savingReport}</div></#if>



<div class="form-group mt-3">
    <form method="post">

        <input type="hidden" name="id" value="<#if card?? && card.id??>${card.id}</#if>">

        <div class="form-group">
            <input type="text" name="cardUser"
                   class="form-control ${(cardUserError??)?string('is-invalid', '')}"
                   value="<#if card??>${card.cardUser}</#if>" placeholder="Enter the name of card user">
            <#if cardUserError??>
                <div class="invalid-feedback">
                    ${cardUserError}
                </div>
            </#if>
        </div>
        <div class="form-group">
            <input type="text" name="cardNumder"
                   class="form-control ${(cardNumderError??)?string('is-invalid', '')}"
                   value="<#if card??>${card.cardNumder}</#if>" placeholder="Enter the number of card">
            <#if cardNumderError??>
                <div class="invalid-feedback">
                    ${cardNumderError}
                </div>
            </#if>
        </div>
        <div class="form-group">
            <input type="date" name="usageTime"
                   class="form-control ${(usageTimeError??)?string('is-invalid', '')}"
                   value="<#if card??>${card.usageTime}</#if>" placeholder="Enter the usageTime">
            <#if usageTimeError??>
                <div class="invalid-feedback">
                    ${usageTimeError}
                </div>
            </#if>
        </div>
        <div class="form-group">
            <input type="number" name="cash"
                   class="form-control ${(cashError??)?string('is-invalid', '')}"
                   value="<#if card??>${card.cash}</#if>"
                   placeholder="Enter the cash">
            <#if cashError??>
                <div class="invalid-feedback">
                    ${cashError}
                </div>
            </#if>
        </div>
        <div class="form-group">
            <input type="checkbox" name="isBlocked"
                   class="form-control ${(isBlockedError??)?string('is-invalid', '')}"
                   value="<#if card??>${card.isBlocked}</#if>"
                   placeholder="is Blocked">
            <#if isBlockedError??>
                <div class="invalid-feedback">
                    ${isBlockedError}
                </div>
            </#if>
        </div>



        <div class="form-group mt-2">
            <button type="submit" class="btn btn-primary btn-lg btn-block>
                <#if !card??>
                    Create
                <#else>
                    Update
                </#if>
            </button>
        </div>
        <#--<input type="hidden" name="_csrf" value="${_csrf.token}" />-->
    </form>
</div>