<?php

namespace App\Form;

use App\Entity\LivreAudio;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Gregwar\CaptchaBundle\Type\CaptchaType;


class LivreAudioType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('titre')
            ->add('editeur')
            ->add('duree')
            ->add('source')
            ->add('audio', FileType::class, array('data_class' => null))
            ->add('image', FileType::class, array('data_class' => null))
            ->add('categorie');
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
            'data_class' => LivreAudio::class,
        ]);
    }
}
