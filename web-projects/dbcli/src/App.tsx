import { Button, ConfigProvider, Dropdown, Flex, MenuProps, Space, theme } from 'antd';

function App() {

  const items: MenuProps['items'] = [
    {
      key: '1',
      label: '打开',
    },
  ];


  return <ConfigProvider
    theme={{
      algorithm: [
        // theme.compactAlgorithm, theme.darkAlgorithm
      ],
      token: {
        borderRadius: 0,
        borderRadiusLG: 0,
        borderRadiusOuter: 0,
        borderRadiusXS: 0,
        borderRadiusSM: 0,
      }
    }}
  >
    <Flex vertical>
      <Flex>
        <Dropdown menu={{ items }} trigger={['click']}>
          <Button type='text'>文件</Button>
        </Dropdown>
        <Dropdown menu={{ items }} trigger={['click']}>
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
