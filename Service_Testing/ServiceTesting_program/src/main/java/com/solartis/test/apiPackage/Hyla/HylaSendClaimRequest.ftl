
{
  <#list policy as result>"${result.atrib}":"${result.value}",</#list>
       
  "deviceInformation" : [ {
     <#list deviceInformationDetail as result>"${result.atrib}":"${result.value}",</#list>
       
    "loss" : [ {
      "claim" : [ {
        <#list ClaimDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
       
        "settlementDetail" : {
          "replacementDeviceInfo" : {
           <#list replacementDeviceInfoDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        
            "replacementDeviceShipmentInfo" : {
              <#list replacementDeviceShipmentInfoDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        
            }
          },
          "rmaDeviceInfo" : {
           <#list rmaDeviceInfoDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
         
            "rmaDeviceShipmentInfo" : {
             <#list rmaDeviceShipmentInfoDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
         
            }
          },
          "incorrectDeviceInfo" : {
            "incorrectDeviceShipmentInfo" : {
             <#list incorrectDeviceShipmentInfoDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        
            }
          }
        },
        "repairVendor" : [ {
          <#list repairVendorDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        
        } ]
      } ]
    } ]
  } ],
     "ServiceRequestDetail": {
     <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }
    }    