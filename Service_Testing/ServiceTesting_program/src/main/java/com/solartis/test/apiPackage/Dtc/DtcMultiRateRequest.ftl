{ 
    <#list EndClientUserUniqueSessionId as result>    
    "${result.atrib}":"${result.value}",
    </#list>
 
    
    
      "ServiceRequestDetail": 
      {
      <#list ServiceRequestDetail as result>
    
      "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
    
      </#list>
       },
       
           "QuoteInformation": 
           {
         <#list QuoteInformation as result>
    
         "${result.atrib}":"${result.value}",
    
         </#list>
          
          "PlanInformation": 
         [
           {
         <#list ATP as result>
         "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
          </#list>
           },
           {
         <#list RC as result>
         "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
          </#list>
           },
           {
         <#list BA as result>
         "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
          </#list>
           },
           {
         <#list PA as result>
         "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
          </#list>
           }, 
           {
         <#list MOA as result>
         "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
          </#list>
           },
          {
         <#list CP as result>
         "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
          </#list>
           },
           {
         <#list PR as result>
         "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
          </#list>
           }
           
        ],
       
       
       
    
       "TravelerList" :
     [
     {
    <#list TravelerList as result>
    
    "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
    
    </#list>
     }
     ]


    }
  }