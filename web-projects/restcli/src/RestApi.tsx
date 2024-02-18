import { SendOutlined } from '@ant-design/icons'
import { Button, Flex, Input, Select, Tabs } from 'antd'
import { styled } from 'styled-components'
import { RequestBody } from './RequestBody'
import { RequestHeader } from './RequestHeader'
import { RequestParameter } from './RequestParameter'

const SRestApi = styled.div`
  display: flex;
  height: 100%;
  .request {
    flex-grow: 1;
    flex-direction: column;
    grid-gap: 8px;
    padding: 0 16px;
    .url-bar {
      width: 100%;
      column-gap: 8px;
      align-items: start;
      .method {
        width: 100px;
      }
    }
    .tabs {
      flex-grow: 1;
    }
  }
  .resp {
    flex-grow: 1;
  }
`

export const RestApi = () => {
  return <SRestApi>
    <Flex className='request'>
      <Flex className='url-bar'>
        <Input
          addonBefore={
            <Select className='method' defaultValue='GET'
              options={['GET', 'POST', 'PUT', 'DELETE',].map(v => ({ value: v, label: v }))}
            />
          }
          className='url'
          placeholder='URL'
        />
        <Button type='primary' icon={<SendOutlined />}>Send</Button>
      </Flex>
      <Flex>
        <Tabs className='tabs' items={[
          { label: 'Parameters', children: <RequestParameter />, key: '3' },
          { label: 'Authentication', children: 'Content of new Tab', key: '1' },
          { label: 'Headers', children: <RequestHeader />, key: '2' },
          {
            label: 'Body', children: <RequestBody />, key: '4'
          }
        ]}>
        </Tabs>
      </Flex>
    </Flex>
    <Flex className='resp' vertical>
      <Input.TextArea rows={16} />
    </Flex>
  </SRestApi>
}