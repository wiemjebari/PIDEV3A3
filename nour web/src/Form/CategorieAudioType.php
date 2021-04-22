<?php

namespace App\Form;

use App\Entity\CategorieAudio;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Gregwar\CaptchaBundle\Type\CaptchaType;




class CategorieAudioType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom')
            ->add('description');
        $builder->add('captcha', 'Gregwar\CaptchaBundle\Type\CaptchaType',array(
            'width' => 200,
            'height' => 50,
            'length' => 6,
            'quality' => 90,
            'distortion' => true,
            'background_color' => [115, 194, 251],
            'max_front_lines' => 0,
            'max_behind_lines' => 0,
            'attr' => array('class' => 'form-control',
                'rows'=> "6"
            )
        ));





        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => CategorieAudio::class,
        ]);
    }
}
