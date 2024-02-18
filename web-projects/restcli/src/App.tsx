import { Button, ConfigProvider, Layout, Tooltip, theme } from 'antd'
import './App.css'
import { styled } from 'styled-components'
import { BookOutlined } from '@ant-design/icons'
import { RestApi } from './RestApi'

const SApp = styled(Layout)`
  display: grid;
  grid-template-columns: 50px auto;
  grid-template-rows: 50px auto;
  height: 100%;
  .side {
    grid-row-start: 1;
    grid-row-end: 3;
    padding: 16px 0;
    display: flex;
    flex-direction: column;
    row-gap: 8px;
  }
  .head {

  }
  .content {

  }
`

function App() {

  return (
    <ConfigProvider theme={{ algorithm: [theme.darkAlgorithm,] }}>
      <SApp>
        <div className='side'>
          <Tooltip title="Rest Api" placement='right'>
            <Button shape='circle' icon={<BookOutlined />} size='large' />
          </Tooltip>
          <Button shape='circle' icon={<BookOutlined />} size='large' />
          <Button shape='circle' icon={<BookOutlined />} size='large' />
          <Button shape='circle' icon={<BookOutlined />} size='large' />
          <Button shape='circle' icon={<BookOutlined />} size='large' />
          <Button shape='circle' icon={<BookOutlined />} size='large' />
        </div>
        <div className='head'></div>
        <div className='content'>
          <RestApi />
        </div>
      </SApp>
    </ConfigProvider>
  )
}

export default App
