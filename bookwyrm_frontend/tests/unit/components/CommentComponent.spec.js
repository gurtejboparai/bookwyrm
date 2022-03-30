import {shallowMount} from '@vue/test-utils'
import CommentComponent from '@/components/CommentComponent.vue'

describe('CommentComponent.vue Test', ()=> {

    it('renders the component', ()=> {
        const wrapper = shallowMount(CommentComponent, {
            propsData: {
                    'commentId': '',
                    'author': '',
                    'content': '',
                    'anonymousFlag': false
                }
            }
        )
        expect(wrapper.vm.$options.name).toMatch('CommentComponent')
    })
    it('processes valid prop data', ()=> {
        const wrapper = shallowMount(CommentComponent, {
            propsData:{
                'commentId': '',
                'author': 'johndoe',
                'content': 'Comment on it!',
                'anonymousFlag': ''
            }
        })

        expect(wrapper.vm.author).toMatch('johndoe')
        expect(wrapper.vm.content).toMatch('Comment on it!')
    })
})