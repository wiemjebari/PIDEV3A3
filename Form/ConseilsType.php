<?php

namespace App\Form;

use App\Entity\Conseils;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ConseilsType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('Titre_article', TextType::class,[
                'label' => false
            ])
            ->add('description', TextareaType::class,[
               'attr' => ['cols' => '5', 'rows' => '5']
            ])
            ->add('image', FileType::class,[
                'label' =>'image',
                'required' => false,
                    'data_class' =>null
                ]
                )
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Conseils::class,
        ]);
    }
}
