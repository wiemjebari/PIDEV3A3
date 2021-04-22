<?php

namespace App\Form;

use App\Entity\LivreAudio;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

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
            ->add('categorie')

        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => LivreAudio::class,
        ]);
    }
}
