import {shallowMount, createLocalVue} from '@vue/test-utils'
import userComponent from '@/components/userComponent.vue'
import {createStore} from 'vuex'
import {createApp} from 'vue'


const store = createStore({
    data() {
    return {
        accountName: ""
    }
}
})
const app = createApp(userComponent)
app.use(store)

describe('userComponent.vue Test', ()=> {


    it('renders the component', ()=> {
        const wrapper = shallowMount(userComponent, {
            global: {
                plugins: [store]
            }
        })
        expect(wrapper.vm.$options.name).toMatch('userComponent')
    })

    it('processes valid prop data', ()=> {
        const wrapper = shallowMount(userComponent, {
            global: {
                plugins: [store]
            }
        })
        wrapper.setData({accountName: 'johndoe'})
        expect(wrapper.vm.accountName).toMatch('johndoe')
    })
})