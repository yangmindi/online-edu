package com.guli.cmsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author ymd
 * @since 2020-07-06
 */
public interface CrmBannerService extends IService<CrmBanner> {

    CrmBanner getBannerById(String id);

    void pageBanner(Page<CrmBanner> pageParam, Object o);

    void saveBanner(CrmBanner banner);

    void updateBannerById(CrmBanner banner);

    void removeBannerById(String id);

    List<CrmBanner> selectIndexList();
}
