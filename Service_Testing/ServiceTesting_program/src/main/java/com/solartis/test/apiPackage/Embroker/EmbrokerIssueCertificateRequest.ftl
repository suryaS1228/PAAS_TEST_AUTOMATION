<#assign NumofTFS=[]><#list NoofTFS as x><#assign NumofTFS=NumofTFS+[x.value]></#list>
{   
	"EndClientUserUniqueSessionId": "Uniquesession",
    "EventName": "GeneratePolicyPDF",
    "EventVersion": "V1",
     "ServiceRequestDetail": {
     <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        },
        "Policy":
         {   
          <#list Policy as result>"${result.atrib}":"${result.value}", </#list>
          "TFSList": 
               {
               "TFSDetail": [
               <#assign i=1>
        <#list 1..NumofTFS[0] as result>
        <#assign TFSDetails="TFSDetail"+i>
       
               {
               <#list TFSDetails?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }<#if result?is_last><#else>,</#if><#assign i=i+1>
        </#list>
               ],
                     
               "DocumentTypeList": [
                {
                  "DocumentType": "ISSUANCE"
                 }
                ],
            "FormsList": [
            {
          <#list Formlist_Declaration as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        },  
           {
          <#list Formlist_GeneralTC as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }, 
         {
          <#list Formlist_DO as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }, 
         {
          <#list Formlist_EPL as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }, 
         {
          <#list Formlist_FID as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }, 
         {
          <#list Formlist_Definition as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }
        ]
        }
        }
        }  
                                        
                                 
                                       
                                             
                                                   
                                                         
                                                               
                                                                           
               
        