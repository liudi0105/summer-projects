import { Button, ConfigProvider, Dropdown, Flex, MenuProps, theme } from 'antd';

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
        theme.compactAlgorithm
      ],
      token: {
      },
      components: {
        Button: {
        }
      }
    }}
  >
    <Flex vertical>
      <Flex>
        <Dropdown menu={{ items: fileMenus }} trigger={['click']}>
          <Button type='text'>文件</Button>
        </Dropdown>
        <Dropdown menu={{ items: fileMenus }} trigger={['click']}>
          <Button type='text'>工具</Button>
        </Dropdown>
      </Flex>
      <Flex>
        <Flex>side</Flex>
        <Flex>content</Flex>
      </Flex>
    </Flex>
  </ConfigProvider>
}

export default App;
