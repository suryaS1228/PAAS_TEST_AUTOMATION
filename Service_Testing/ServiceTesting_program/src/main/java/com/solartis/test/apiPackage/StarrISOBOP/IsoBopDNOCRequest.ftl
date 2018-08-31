 {
      "EndClientUserUniqueSessionId": "Uniqusession",
      "OwnerId": "24",
      "ServiceRequestDetail": {
        <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
      },
      "Notice": {
        <#list Notice as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
      }
    }
