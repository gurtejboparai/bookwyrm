import {shallowMount} from '@vue/test-utils'
import BookBriefComponent from '@/components/BookBriefComponent.vue'

describe('BookBriefComponent.vue Test', ()=> {

    it('renders the component', ()=> {
        const wrapper = shallowMount(BookBriefComponent, {
            propsData:{
                'title': '',
                'author': ''
            }
        })
        expect(wrapper.vm.$options.name).toMatch('BookBriefComponent')
    })
    it('processes valid prop data', ()=> {
        const wrapper = shallowMount(BookBriefComponent, {
            propsData:{
                'title': 'A Game of Thrones',
                'author': 'George R. R. Martin'
            }
        })

        expect(wrapper.vm.title).toMatch('A Game of Thrones')
        expect(wrapper.vm.author).toMatch('George R. R. Martin')
    })
})