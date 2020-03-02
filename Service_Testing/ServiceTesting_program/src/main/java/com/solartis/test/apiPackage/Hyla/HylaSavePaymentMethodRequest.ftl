{     
     "ServiceRequestDetail": {
     <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        },
        "paymentInformation":
         {   
          <#list paymentInformation as result>"${result.atrib}":"${result.value}" </#list>
          },
     
      <#list Policy as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>  
    }