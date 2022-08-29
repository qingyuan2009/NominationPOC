package com.sap.s4hana.eureka.business.nomination.tenantlcm.worker;

import com.sap.s4hana.eureka.framework.tenant.lcm.action.provision.AppTenantProvisionWorker;
import com.sap.s4hana.eureka.framework.tenant.lcm.action.provision.ProvisionBizPayLoad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NominationProvisionWorker implements AppTenantProvisionWorker {

    @Override
    public void doProvision(ProvisionBizPayLoad provisionBizPayLoad) {
        log.info(
                "Do provision: Company: {}; E-mail: {}",
                provisionBizPayLoad.getCompanyName(),
                provisionBizPayLoad.getAdminEmail());
    }
}
