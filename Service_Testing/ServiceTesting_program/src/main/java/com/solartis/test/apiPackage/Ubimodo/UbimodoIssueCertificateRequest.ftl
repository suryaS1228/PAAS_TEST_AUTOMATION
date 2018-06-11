<#assign Numofdependent=[]><#list Noofdependent as x><#assign Numofdependent=Numofdependent+[x.value]></#list>
<#assign Numofbeneficiary=[]><#list Noofbeneficiary as x><#assign Numofbeneficiary=Numofbeneficiary+[x.value]></#list>
{    
	"EndClientUserUniqueSessionId" : "Uniquesession",
     "EndClientUserBrowserSessionId" : "7Eadfad4444",
     "EndClientUserBrowserInformation" : "Mozilla/...",
     "EventName" : "IssueCertificate_Workflow",
     "PaaSClientIPAddress" : "125.25.25.2",
     "ClientUniqueRequestID" : "1",
     "ServiceRequestDetail": {
     <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        },
        "GroupPolicy":
         {   
          <#list GroupPolicy as result>"${result.atrib}":"${result.value}", </#list>
          "Certificate": 
               {
                 <#list Certificate as result>"${result.atrib}":"${result.value}", </#list>
              "MemberDetails": 
              {
                  <#list MemberDetails as result>"${result.atrib}":"${result.value}", </#list>
              "Communication" : 
              {
                <#list Communication1 as result>"${result.atrib}":"${result.value}", </#list>
                "Phone" : 
                {
                  <#list Phone1 as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
                }
              }  
              
             },
          
             "BillingInfo": 
             {
           <#list MemberDetails as result>"${result.atrib}":"${result.value}", </#list>
              "Communication" :
               {
           <#list Communication2 as result>"${result.atrib}":"${result.value}", </#list>
                "Phone" : 
                {
           <#list Phone2 as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
                }
              }  
            },
           
            "DependentInfo": 
            [        
          <#assign i=1>
        <#list 1..Numofdependent[0] as result>
        <#assign Dependents="Dependent"+i>
        {
         "DependentDetails" :  
         {
         <#list Dependents?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }}<#if result?is_last><#else>,</#if><#assign i=i+1>
        </#list>
       ],
       
       "BeneficiaryInfo":  
       [
          <#assign i=1>
        <#list 1..Numofbeneficiary[0] as result>
        <#assign Beneficiarys="Beneficiary"+i>
        {
        "BeneficiaryDetails" :     
        {
         <#list Beneficiarys?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }}<#if result?is_last><#else>,</#if><#assign i=i+1>
        </#list>
       ],
       
       "PaymentInfo":
        {
           <#list PaymentInfo as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
       }   
      }
     }
    }