<?php

namespace App\Form;

use App\Entity\PropertySearch;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextType;


class PropertySearchType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom',null,['label' => 'Trouver une catégorie',
                'attr' => ['required' => false,
                    'placeholder' => 'Entrer le nom '] ] ) ;
    }


    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => PropertySearch::class,
            'method' => 'get',
            'csrf_protection' => false
        ]);
    }
}
