import {shallowMount} from '@vue/test-utils'
import SearchBarComponent from '@/components/SearchBarComponent.vue'

describe('SearchBarComponent.vue Test', ()=> {

    it('renders the component', ()=> {
        const wrapper = shallowMount(SearchBarComponent, {
            data() {
                return {
                    searchString: ""
                }
            }
        })
        expect(wrapper.vm.$options.name).toMatch('SearchBarComponent')
    })
    it('processes valid prop data', ()=> {
        const wrapper = shallowMount(SearchBarComponent, {
            data() {
                return {
                    searchString: ""
                }
            }
        })
        wrapper.setData({searchString: 'Game of Thrones'})
        expect(wrapper.vm.searchString).toMatch('Game of Thrones')
    })
    
})