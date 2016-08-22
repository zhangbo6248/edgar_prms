package com.sjn_edgar.prms.service.shiro.impl;
/*
* Copyright (c) 2016 www.51cjhb.com. All Rights Reserved.
*/
import com.sjn_edgar.prms.dao.shiro.PmsMenuDao;
import com.sjn_edgar.prms.dao.shiro.PmsMenuRoleDao;
import com.sjn_edgar.prms.domain.shiro.PmsMenu;
import com.sjn_edgar.prms.domain.shiro.PmsMenuRole;
import com.sjn_edgar.prms.service.shiro.PmsMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**@Title:      菜单service接口实现
 * @Description:  <p> </p>
 * @author         edgar【zbo@51qmz.cn】
 * @version        V 1.0
 * @Date           2016-8-22 11:18:26
 */
@Service("pmsMenuService")
public class PmsMenuServiceImpl implements PmsMenuService {

	@Autowired //菜单
	private PmsMenuDao pmsMenuDao;
	@Autowired //菜单角色
	private PmsMenuRoleDao pmsMenuRoleDao;

	/**
	 * 保存菜单PmsMenuDao
	 * @param menu
	 */
	public void savaMenu(PmsMenu menu) {
		pmsMenuDao.insert(menu);
	}

	/**
	 * 根据父菜单ID获取该菜单下的所有子孙菜单.<br/>
	 * @param parentId (如果为空，则为获取所有的菜单).<br/>
	 * @return menuList.
	 */
	@SuppressWarnings("rawtypes")
	public List getListByParent(Long parentId) {
		return pmsMenuDao.listByParent(parentId);
	}

	/**
	 * 根据id删除菜单
	 */
	public void delete(Long id) {
		this.pmsMenuDao.delete(id);
	}

	/**
	 * 根据角色id串获取菜单
	 * @param roleIdsStr
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List listByRoleIds(String roleIdsStr) {
		return this.pmsMenuDao.listByRoleIds(roleIdsStr);
	}

	/**
	 * 根据菜单ID查找菜单（可用于判断菜单下是否还有子菜单）.
	 * @param parentId
	 * @return menuList.
	 */
	public List<PmsMenu> listByParentId(Long parentId) {
		return pmsMenuDao.listByParentId(parentId);
	}

	/***
	 * 根据名称和是否叶子节点查询数据
	 * @param map
	 * map.get("isLeaf") 是否是叶子节点
	 * map.get("name") 节点名称
	 * @return
	 */
	public List<PmsMenu> getMenuByNameAndIsLeaf(Map<String, Object> map) {
		return pmsMenuDao.getMenuByNameAndIsLeaf(map);
	}

	/**
	 * 根据菜单ID获取菜单.
	 * @param pid
	 * @return
	 */
	public PmsMenu getById(Long pid) {
		return pmsMenuDao.getById(pid);
	}

	/**
	 * 更新菜单.
	 * @param menu
	 */
	public void update(PmsMenu menu) {
		pmsMenuDao.update(menu);

	}

	/**
	 * 根据角色查找角色对应的菜单ID集
	 * @param roleId
	 * @return
	 */
	public String getMenuIdsByRoleId(Long roleId) {
		List<PmsMenuRole> menuList = pmsMenuRoleDao.listByRoleId(roleId);
		StringBuffer menuIds = new StringBuffer("");
		if (menuList != null && !menuList.isEmpty()) {
			for (PmsMenuRole rm : menuList) {
				menuIds.append(rm.getMenuId()).append(",");
			}
		}
		return menuIds.toString();

	}
}
