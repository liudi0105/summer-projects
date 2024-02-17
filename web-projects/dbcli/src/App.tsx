import { Button, ConfigProvider, Dropdown, Flex, Layout, MenuProps, Tree, theme } from 'antd';
import { SideBar } from './SideBar';
import styled from 'styled-components';

const SLayout = styled(Layout)`
  display: grid;
  grid-template-columns: 1fr auto;
  grid-template-rows: 1fr auto;
  .header {
    grid-column-start: 1;
    grid-column-end: 3;
  }
  .sider {
    overflow: auto;
  }
`

function App() {

  const fileMenus: MenuProps['items'] = [
    { key: '1', label: '最近的编辑', },
    { key: '2', label: '打开脚本', },
    { key: '3', label: '刷新', },
    { key: '4', label: '退出', },
  ];


  return <ConfigProvider
    theme={{
      algorithm: [
        theme.compactAlgorithm, theme.darkAlgorithm
      ],
      token: {
      },
      components: {
        Button: {
        }
      }
    }}
  >
    <SLayout>
      <div className='header'>
        <Dropdown menu={{ items: fileMenus }} trigger={['click']}>
          <Button type='text'>文件</Button>
        </Dropdown>
        <Dropdown menu={{ items: fileMenus }} trigger={['click']}>
          <Button type='text'>工具</Button>
        </Dropdown>
      </div>
      <div className='sider'>
        <SideBar />
      </div>
      <div className='content'>content</div>
    </SLayout>
  </ConfigProvider>
}

export default App;
