import React from 'react';
import { Menu, Icon } from 'antd';

const SubMenu = Menu.SubMenu;
const MenuItemGroup = Menu.ItemGroup;

const TopMenu = (props) => {
  return (
    <Menu mode="horizontal" theme="dark" defaultSelectedKeys={['none']}>
      <div>{props.title}</div>
      <Menu.Item key="none">
        统计报表
      </Menu.Item>
    </Menu>
  );
};

TopMenu.propTypes = {
};

export default TopMenu;
