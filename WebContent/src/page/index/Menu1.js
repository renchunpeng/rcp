import { Menu, ActivityIndicator, NavBar } from 'antd-mobile'
import React from 'react'

class Menu1 extends React.Component {
  constructor(...args) {
    super(...args);
    this.state = {
      initData: this.props.menuList,
      show: true,
    };
  }
  onChange = (value) => {
    let label = '';
    this.state.initData.forEach((dataItem) => {
      if (dataItem.value === value[0]) {
        label = dataItem.label;
        if (dataItem.children && value[1]) {
          dataItem.children.forEach((cItem) => {
            if (cItem.value === value[1]) {
              label += ` ${cItem.label}`;
            }
          });
        }
      }
    });
    console.log(label);
    //点击选中之后菜单收回
    this.props.todo({
      type: 'cla/changeDraw'
    })
  }

  handleClick = (e) => {
    e.preventDefault(); // 修复 Android 上点击穿透
    this.setState({
      show: !this.state.show,
    });
    // mock for async data loading
    if (!this.state.initData) {
      let _menuList = this.props.menuList;
      let list = [];
      for (let i = 0; i < _menuList.length; i++) {
        list[i] = {
          value: i + 1,
          label: _menuList[i].prdtypename,
          isLeaf: true,
        }
      }
      setTimeout(() => {
        this.setState({
          initData: list,
        });
      }, 2000);
    }
  }

  render() {
    const { initData, show } = this.state;
    const menuEl = (
      <Menu
        className="foo-menu"
        data={initData}
        level={1}
        value={['2', '22']}
        onChange={this.onChange}
        height={document.documentElement.clientHeight * 0.7}
      />
    );
    const loadingEl = (
      <div style={{ width: '100%', height: document.documentElement.clientHeight * 0.6, display: 'flex', justifyContent: 'center' }}>
        <ActivityIndicator size="large" />
      </div>
    );
    return (
      <div className="menu-active">
        {show ? initData ? menuEl : loadingEl : null}
      </div>
    );
  }
}

export default Menu1