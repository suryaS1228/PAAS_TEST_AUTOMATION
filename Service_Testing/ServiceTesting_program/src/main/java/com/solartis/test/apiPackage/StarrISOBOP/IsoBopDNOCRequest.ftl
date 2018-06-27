 {
      "EndClientUserUniqueSessionId": "Uniqusession",
      "OwnerId": "24",
      "ServiceRequestDetail": {
        <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}", 
		</#list>
      },
      "Notice": {
        <#list Notice as result>"${result.atrib}":"${result.value}", 
		</#list>
      }
    }
