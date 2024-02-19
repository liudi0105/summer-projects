import { DatabaseOutlined, EnvironmentOutlined, HistoryOutlined, SettingOutlined } from '@ant-design/icons'
import { Button, ConfigProvider, Flex, Layout, Tooltip, theme } from 'antd'
import { styled } from 'styled-components'
import './App.css'
import { RestApi } from './RestApi'

const SApp = styled(Layout)`
  display: grid;
  grid-template-columns: 50px auto;
  grid-template-rows: 50px auto;
  height: 100%;
  .side {
    padding: 16px 0;
    row-gap: 4px;
  }
  .head {
    grid-column-start: 1;
    grid-column-end: 3;
    padding: 0 24px;
  }
  .content {
  }
`

function App() {
  return (
    <ConfigProvider theme={{ algorithm: [theme.darkAlgorithm,] }}>
      <SApp>
        <Flex className='head' justify='end' align='center' gap={8}>
          <Button icon={<SettingOutlined />} type='text' />
          <Button type='text'>Sign In</Button>
        </Flex>
        <Flex className='side' vertical align='center'>
          <Tooltip title="Collections" placement='right'>
            <Button icon={<DatabaseOutlined />} size='large' />
          </Tooltip>
          <Tooltip title="Environments" placement='right'>
            <Button icon={<EnvironmentOutlined />} size='large' />
          </Tooltip>
          <Tooltip title="History" placement='right'>
            <Button icon={<HistoryOutlined />} size='large' />
          </Tooltip>
        </Flex>
        <div className='content'>
          <RestApi />
        </div>
      </SApp>
    </ConfigProvider>
  )
}

export default App
